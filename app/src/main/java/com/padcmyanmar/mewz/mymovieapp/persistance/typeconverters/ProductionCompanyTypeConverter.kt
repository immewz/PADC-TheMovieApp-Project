package com.padcmyanmar.mewz.mymovieapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.mymovieapp.data.vos.ProductionCompaniesVO

class ProductionCompanyTypeConverter {

    @TypeConverter
    fun toString(productionCompany:List<ProductionCompaniesVO>?): String{
        return Gson().toJson(productionCompany)
    }

    @TypeConverter
    fun toProductionCompaniesV0(commentListJsonStr: String): List<ProductionCompaniesVO>?{
        val productionCompaniesVOType = object : TypeToken<List<ProductionCompaniesVO>?>() {}.type
        return Gson().fromJson(commentListJsonStr, productionCompaniesVOType)
    }
}