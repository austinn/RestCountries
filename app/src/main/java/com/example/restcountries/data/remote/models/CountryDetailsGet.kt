package com.example.restcountries.data.remote.models

class CountryDetailsGet(
    val name: CountryName,
    val capital: List<String>?,
    val population: Int,
    val area: Double,
    val region: String,
    val subregion: String,
)