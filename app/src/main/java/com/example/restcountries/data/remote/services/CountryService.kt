package com.example.restcountries.data.remote.services

import com.example.restcountries.data.remote.models.CountryListGet
import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {
    @GET("all?fields=name")
    suspend fun getCountryList(): Response<ArrayList<CountryListGet>>
}