package com.padcmyanmar.mewz.mymovieapp.mvp.presenters

import com.padcmyanmar.mewz.mymovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.mvp.views.MainView

interface MainPresenter: IBasePresenter, BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
MovieViewHolderDelegate{
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}