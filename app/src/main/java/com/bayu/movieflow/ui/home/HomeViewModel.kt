package com.bayu.movieflow.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu.movieflow.core.data.repository.vo.Resource
import com.bayu.movieflow.core.data.source.local.preferences.PreferenceManager
import com.bayu.movieflow.core.domain.model.Genre
import com.bayu.movieflow.core.domain.model.Movie
import com.bayu.movieflow.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val session: PreferenceManager,
) : ViewModel() {

    private val query = MutableStateFlow("a")

    private val _genreSelectedId = session.getPreferenceInt(PreferenceManager.KEY_SELECTED_GENRE)

    private val _movies: MutableStateFlow<Resource<List<Movie>>> =
        MutableStateFlow(Resource.Loading())
    private val _genres: MutableStateFlow<List<Genre>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            launch {
                query
                    .flatMapLatest { movieUseCase.searchMovies(it) }
                    .collectLatest { _movies.value = it }
            }
            launch {
                movieUseCase.genres().collect {
                    _genres.value = it
                }
            }
        }
    }

    val genres: StateFlow<List<Genre>> = _genreSelectedId.flatMapLatest { id ->
        _genres.map {
            it.map { genre ->
                if (genre.id == id) {
                    genre.copy(isSelected = id)
                } else {
                    genre
                }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val movies: StateFlow<Resource<List<Movie>>> = _genreSelectedId.flatMapLatest { id ->
        if (id == 0) _movies else filterMovies(id)
    }.stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(), Resource.Loading())

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun setGenreId(id: Int) {
        viewModelScope.launch {
            val result = if (id < 0) 0 else id
            session.setPreferenceInt(PreferenceManager.KEY_SELECTED_GENRE, result)
        }
    }

    private suspend fun filterMovies(id: Int): Flow<Resource<List<Movie>>> = coroutineScope {
        _movies.map { result ->
            if (result !is Resource.Success)
                return@map result
            else
                return@map Resource.Success(result.data.filter { it.genreIds.contains(id) })
        }
    }

}