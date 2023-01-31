package com.example.restcountries.data.remote.models

data class CountryListGet (
    val name: CountryName,
)

data class CountryName(
    val common: String,
    val official: String,
)