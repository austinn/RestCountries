package com.example.restcountries.data.remote.models

import com.example.restcountries.data.entities.Flags

data class CountryListGet(
    val name: CountryName,
    val flags: Flags? = null
)

data class CountryName(
    val common: String,
    val official: String,
)