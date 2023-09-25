package com.padcmyanmar.mewz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl: ViewModel(), MovieDetailPresenter {

    var mView: MovieDetailView? = null

    private var mMovieModel: MovieModel = MovieModelImpl

    override fun initView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
        mMovieModel.getMovieDetail(movieId){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetail(it)
            }
        }

        mMovieModel.getCreditsByMovie(movieId,
        onSuccess = { mView?.showCreditsByMovie(cast = it.first, crew =  it.second)},
        onFailure = { mView?.showError(it) })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}