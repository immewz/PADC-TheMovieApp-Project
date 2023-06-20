package com.padcmyanmar.mewz.mymovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.mymovieapp.data.vos.ProductionCountriesVO

class ProductionCountryTypeConverter {

    @TypeConverter
    fun toString(productionCountry: List<ProductionCountriesVO>?): String{
        return Gson().toJson(productionCountry)
    }

    @TypeConverter
    fun  toProductionCountriesVO(commentListJsonStr: String): List<ProductionCountriesVO>? {
        val productionCountriesVoType = object : TypeToken<List<ProductionCountriesVO?>>() {}.type
        return Gson().fromJson(commentListJsonStr, productionCountriesVoType)
    }
}