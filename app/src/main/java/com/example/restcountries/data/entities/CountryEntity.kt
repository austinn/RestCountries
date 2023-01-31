package com.example.restcountries.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val name: String,
    val capital: String? = null,
    val population: Int? = null,
    val area: Double? = null,
    val region: String? = null,
    val subregion: String? = null,
    val latlng: List<Double>? = null,
    val flag: String? = null,
)

data class Flags(
    val svg: String?,
    val png: String?,
)

//val countryList = listOf(
//    CountryEntity(
//        id = 0,
//        name = "Afghanistan",
//        capital = "Kabul",
//    ),
//    CountryEntity(
//        id = 1,
//        name = "Åland Islands",
//        capital = "Mariehamn",
//    ),
//    CountryEntity(
//        id = 2,
//        name = "Albania",
//        capital = "Tirana",
//    ),
//    CountryEntity(
//        id = 3,
//        name = "Algeria",
//        capital = "Algiers",
//    ),
//)