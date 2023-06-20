package com.padcmyanmar.mewz.mymovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.mewz.mymovieapp.adapters.ActorAdapter
import com.padcmyanmar.mewz.mymovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewPodActorListBinding

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var binding: ViewPodActorListBinding
    private lateinit var mActorAdapter: ActorAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodActorListBinding.bind(this)
    }

    fun setUpActorListViewPod(){
        setUpActorRecyclerView()
    }

    fun setData(actors: List<ActorVO>){
        mActorAdapter.setNewData(actors)
    }

    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String){
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        binding.tvBestActor.text = titleText
        binding.tvMoreActors.text = moreTitleText
        binding.tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setUpActorRecyclerView() {
        mActorAdapter = ActorAdapter()
        binding.rvBestActor.adapter = mActorAdapter
        binding.rvBestActor.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
    }
}