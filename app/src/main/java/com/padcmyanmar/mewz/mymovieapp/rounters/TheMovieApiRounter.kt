package com.padcmyanmar.mewz.mymovieapp.rounters

import android.app.Activity
import com.padcmyanmar.mewz.mymovieapp.activities.MovieDetailActivity
import com.padcmyanmar.mewz.mymovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailActivity(movieId: Int){
    startActivity(MovieDetailActivity.newIntent(this, movieId))
}

fun Activity.navigateToMovieSearchActivity(){
    startActivity(MovieSearchActivity.newIntent(this))
}