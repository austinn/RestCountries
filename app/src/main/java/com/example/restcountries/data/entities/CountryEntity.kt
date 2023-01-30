package com.example.restcountries.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,
    val capital: String,
    val region: String? = null,
    val subregion: String? = null,
    val population: Int? = null,
    val latlng: List<Double>? = null,

    @Embedded
    val flags: Flags? = null
)

data class Flags(
    val svg: String,
    val png: String,
)

val countryList = listOf(
    CountryEntity(
        id = 0,
        name = "Afghanistan",
        capital = "Kabul",
    ),
    CountryEntity(
        id = 1,
        name = "Åland Islands",
        capital = "Mariehamn",
    ),
    CountryEntity(
        id = 2,
        name = "Albania",
        capital = "Tirana",
    ),
    CountryEntity(
        id = 3,
        name = "Algeria",
        capital = "Algiers",
    ),
)