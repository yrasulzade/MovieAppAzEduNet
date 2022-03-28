package com.example.movieappazedunet.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappazedunet.R
import com.example.movieappazedunet.databinding.MovieCardBinding
import com.example.movieappazedunet.presentation.util.Utils.generateImageDownloadUrl

class PopularAdapter(
    private val context: Context,
    private val onPopularMovieClick: OnPopularMovieClick,
    private val movies: ArrayList<com.example.movieappazedunet.domain.model.movie.Result>
) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardBinding: MovieCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.movie_card, parent, false
        )
        return ViewHolder(cardBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.title.text = movie.title
        holder.binding.releaseDate.text = movie.release_date


        movie.poster_path?.let {
            Glide.with(context)
                .load(it.generateImageDownloadUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.moviePoster)
        }

        holder.binding.item.setOnClickListener {
            onPopularMovieClick.onMovieClick(movie.id)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(val binding: MovieCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnPopularMovieClick {
        fun onMovieClick(movieId: Long)
    }

}
