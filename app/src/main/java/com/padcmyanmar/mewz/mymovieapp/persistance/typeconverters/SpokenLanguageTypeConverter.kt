package com.padcmyanmar.mewz.mymovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.mymovieapp.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {

    @TypeConverter
    fun toString(spokenLanguage: List<SpokenLanguageVO>?): String{
        return Gson().toJson(spokenLanguage)
    }

    @TypeConverter
    fun toSpokenLanguageVO(commentListJsonStr: String): List<SpokenLanguageVO>? {
        val spokenLanguageVOType = object : TypeToken<List<SpokenLanguageVO?>>() {}.type
        return Gson().fromJson(commentListJsonStr, spokenLanguageVOType)
    }
}