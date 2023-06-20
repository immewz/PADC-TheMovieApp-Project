package com.padcmyanmar.mewz.mymovieapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewItemBannerBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.BannerViewHolderDelegate


class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var binding: ViewItemBannerBinding
    private var mMovieVO: MovieVO? = null

    init {
        binding = ViewItemBannerBinding.bind(itemView)
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
                movie.id?.let { it -> mDelegate.onTapMovieFormBanner(it) }
            }
        }
    }


    fun bindData(movie: MovieVO) {
        this.mMovieVO = movie
        Log.d("BannerViewHolder", "Movie.bannerImage: $${IMAGE_BASE_URL}${movie.posterPath}")
        Log.d("BannerViewHolder", "Movie.bannerMovieName: ${movie.title}")
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivBannerImage)

        binding.tvBannerMovieName.text = movie.title
    }
}