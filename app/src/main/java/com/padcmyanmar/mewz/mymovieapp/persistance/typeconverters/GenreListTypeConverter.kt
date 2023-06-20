package com.padcmyanmar.mewz.mymovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.mymovieapp.data.vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genre: List<GenreVO>?): String{
        return Gson().toJson(genre)
    }

    @TypeConverter
    fun toGenreVo(commentListJsonStr: String): List<GenreVO>?{
        val genreVOType = object : TypeToken<List<GenreVO?>>() {}.type
        return Gson().fromJson(commentListJsonStr, genreVOType)
    }
}