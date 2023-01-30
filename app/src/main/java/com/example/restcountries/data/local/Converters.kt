package com.example.restcountries.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringToListDouble(value: String?): List<Double> {
        val listType = object : TypeToken<List<Double?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListDoubleToString(list: List<Double?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}