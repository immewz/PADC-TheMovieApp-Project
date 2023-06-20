package com.padcmyanmar.mewz.mymovieapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewHolderMovieBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.MovieViewHolderDelegate

class MovieViewHolder(itemView: View,private val mDelegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderMovieBinding
    private var mMovieVO: MovieVO? = null

    init {
        binding = ViewHolderMovieBinding.bind(itemView)
        itemView.setOnClickListener {
            mMovieVO?.let {movie ->
                movie.id.let { it -> mDelegate.onTapMovie(it) }
            }
        }

    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivMovieImage)

        binding.tvMovieName.text = movie.title
        binding.tvMovierating.text = movie.voteAverage?.toString()
        binding.rbMovieRating.rating = movie.getRatingBasedOnFiveStars()
    }
}