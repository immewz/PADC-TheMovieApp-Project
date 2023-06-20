package com.padcmyanmar.mewz.mymovieapp.mvp.views

import com.padcmyanmar.mewz.mymovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO

interface MovieDetailView: BaseView {
    fun showMovieDetail(movie: MovieVO)
    fun showCreditByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}