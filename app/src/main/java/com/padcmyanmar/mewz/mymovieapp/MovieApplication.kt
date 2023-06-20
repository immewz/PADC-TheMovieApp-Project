package com.padcmyanmar.mewz.mymovieapp

import android.app.Application
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}