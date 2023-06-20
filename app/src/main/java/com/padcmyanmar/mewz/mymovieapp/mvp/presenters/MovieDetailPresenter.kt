package com.padcmyanmar.mewz.mymovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.mewz.mymovieapp.mvp.views.MovieDetailView

interface MovieDetailPresenter: IBasePresenter {
    fun initView(view: MovieDetailView)
    fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}