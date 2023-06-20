package com.padcmyanmar.mewz.mymovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.mymovieapp.data.vos.GenreVO

data class GetGenreResponse(

    @SerializedName("genres")
    val genres: List<GenreVO>

)
