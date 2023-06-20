package com.padcmyanmar.mewz.mymovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl
import com.padcmyanmar.mewz.mymovieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl: ViewModel(), MovieDetailPresenter{

    // Model
    private val mMovieModel = MovieModelImpl

    // View
    private var mView: MovieDetailView? = null

    override fun initView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
        // Movie Detail
        mMovieModel.getMovieDetails(movieId = movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetail(it)
            }
        }

        // Credit
        mMovieModel.getCreditsByMovie(movieId = movieId.toString(),
            onSuccess = { mView?.showCreditByMovie(cast = it.first, crew = it.second)},
            onFailure = { mView?.showError(it)}
        )
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}