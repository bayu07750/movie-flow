package com.bayu.movieflow.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bayu.movieflow.R
import com.bayu.movieflow.core.data.repository.vo.Resource
import com.bayu.movieflow.core.domain.model.Genre
import com.bayu.movieflow.core.domain.model.Movie
import com.bayu.movieflow.core.extensions.hide
import com.bayu.movieflow.core.extensions.visible
import com.bayu.movieflow.databinding.FragmentHomeBinding
import com.bayu.movieflow.ui.adapter.MoviesAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private var queryMovies = ""
    private val handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable by lazy {
        Runnable {
            viewModel.setQuery(queryMovies)
        }
    }

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        actions()
    }

    private fun init() {
        moviesAdapter = MoviesAdapter(onItemClicked = onItemClicked)
        setupRecyclerView()
    }

    private val onItemClicked: (Movie, View) -> Unit = { movie, _ ->
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie.urlBackdrop)
        )
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = moviesAdapter
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.movies.collectLatest { result ->
                        with(binding) {
                            when (result) {
                                is Resource.Empty -> showMessage(getString(R.string.not_found))
                                is Resource.Error -> showMessage(result.message)
                                is Resource.Loading -> showProgressBar()
                                is Resource.Success -> {
                                    progressBar.hide()
                                    recyclerView.visible()
                                    tvMessage.hide()
                                    if (result.data.isNotEmpty())
                                        moviesAdapter.submitList(result.data)
                                    else
                                        showMessage(getString(R.string.not_found))
                                }
                            }
                        }
                    }
                }
            }
            launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.genres.collectLatest {
                        if (it.isNotEmpty()) {
                            createChips(it)
                        }
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        with(binding) {
            progressBar.visible()
            recyclerView.hide()
            tvMessage.hide()
        }
    }

    private fun showMessage(message: String) {
        with(binding) {
            progressBar.hide()
            recyclerView.hide()
            tvMessage.visible()
            tvMessage.text = message
        }
    }

    private fun createChips(genres: List<Genre>) {
        with(binding) {
            cgGenres.removeAllViews()
            genres.forEach { genre ->
                val chipDrawable = ChipDrawable.createFromResource(requireContext(), R.xml.chip)
                Chip(requireContext()).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    setChipDrawable(chipDrawable)
                    shapeAppearanceModel = ShapeAppearanceModel.Builder()
                        .setAllCornerSizes(8F)
                        .build()
                    text = genre.name
                    id = genre.id
                    isChecked = genre.id == genre.isSelected
                }.also { chip ->
                    cgGenres.addView(chip)
                }
            }
        }
    }

    private fun actions() {
        with(binding) {
            tilQuerySearch.editText?.addTextChangedListener {
                val text = it?.toString().orEmpty()
                queryMovies = text.ifEmpty { "a" }
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 300)
            }
            cgGenres.setOnCheckedChangeListener { _, checkedId ->
                viewModel.setGenreId(checkedId)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
