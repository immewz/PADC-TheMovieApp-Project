package com.padcmyanmar.mewz.mymovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewHolderShowcaseBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.ShowcaseViewHolderDelegate

class ShowCaseViewHolder(itemView: View,mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderShowcaseBinding
    private var mMovieVO: MovieVO? = null

    init {
        binding = ViewHolderShowcaseBinding.bind(itemView)
        itemView.setOnClickListener {
            mMovieVO?.let {movie->
                movie.id?.let { it -> mDelegate.onTapMovieFromShowcase(it) }
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivShowCase)

        binding.tvShowCaseMovieName.text = movie.title
        binding.tvShowCaseMovieDate.text = movie.releaseDate

    }
}