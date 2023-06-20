package com.padcmyanmar.mewz.mymovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.mymovieapp.data.vos.ActorVO

data class GetActorsResponse(

    @SerializedName("results")
    val results: List<ActorVO>?,

)
