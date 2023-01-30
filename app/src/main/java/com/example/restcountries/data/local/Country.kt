package com.example.restcountries.data.local

data class Country(
    val name: String,
    val capital: String,
    val region: String? = null,
    val subregion: String? = null,
    val population: Int? = null,
    val latlng: List<Double>? = null,
    val flags: Flags? = null
)

data class Flags(
    val svg: String,
    val png: String,
)

val countryList = listOf(
    Country(
        name = "Afghanistan",
        capital = "Kabul",
    ),
    Country(
        name = "Åland Islands",
        capital = "Mariehamn",
    ),
    Country(
        name = "Albania",
        capital = "Tirana",
    ),
    Country(
        name = "Algeria",
        capital = "Algiers",
    ),
)