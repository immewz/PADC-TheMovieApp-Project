package com.padcmyanmar.mewz.mymovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.mymovieapp.data.vos.DateVO
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO

data class MovieListResponse(

    @SerializedName("page")
    val page: Int?,

    @SerializedName("dates")
    val date: DateVO?,

    @SerializedName("results")
    val results: List<MovieVO>?
)
