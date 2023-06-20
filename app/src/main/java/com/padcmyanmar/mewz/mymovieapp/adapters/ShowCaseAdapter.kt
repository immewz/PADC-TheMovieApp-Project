package com.padcmyanmar.mewz.mymovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.viewholders.ShowCaseViewHolder

class ShowCaseAdapter(val mDelegate: ShowcaseViewHolderDelegate): RecyclerView.Adapter<ShowCaseViewHolder>() {

    private var mMovieList: List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcase,parent,false)
        return ShowCaseViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}