package com.padcmyanmar.mewz.mymovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.mymovieapp.data.vos.ActorVO

data class GetCreditsByMovieResponse(

    @SerializedName("cast")
    val cast: List<ActorVO>,

    @SerializedName("crew")
    val crew: List<ActorVO>
)
