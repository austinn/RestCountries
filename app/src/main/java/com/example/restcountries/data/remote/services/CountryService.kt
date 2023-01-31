package com.example.restcountries.data.remote.services

import com.example.restcountries.data.remote.models.CountryDetailsGet
import com.example.restcountries.data.remote.models.CountryListGet
import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    // hardcode queryparam fields for now
    @GET("all?fields=name,flags")
    suspend fun getCountryList(): Response<ArrayList<CountryListGet>>

    @GET("name/{name}")
    suspend fun getCountryDetails(@Path("name") name: String): Response<ArrayList<CountryDetailsGet>>
}