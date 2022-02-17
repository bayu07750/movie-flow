package com.bayu.movieflow.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bayu.movieflow.core.domain.model.Movie
import com.bayu.movieflow.databinding.ItemMovieBinding

class MoviesAdapter(
    private val onItemClicked: (Movie, View) -> Unit,
) : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(DIFF_UTIL) {

    inner class MoviesViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            with(binding) {
                tvTitle.text = movie.title
                ivMovie.load(movie.urlBackdrop) {
                    crossfade(true)
                    crossfade(600)
                    transformations(RoundedCornersTransformation(8F))
                }
                root.setOnClickListener { onItemClicked(movie, ivMovie) }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}