package com.padcmyanmar.mewz.mymovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewHolderActorHolderBinding
import com.padcmyanmar.mewz.mymovieapp.utils.IMAGE_BASE_URL

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderActorHolderBinding

    init {
        binding = ViewHolderActorHolderBinding.bind(itemView)
    }

    fun bindData(actor: ActorVO){
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profilePath}")
            .into(binding.ivBestActor)

        binding.tvActorName.text = actor.name
    }
}