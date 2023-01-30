package com.example.restcountries.data.repositories

import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.data.local.CountryDao
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val localDataSource: CountryDao,
    // private val bridgeService: BridgeService
) {
    suspend fun getCountryById(id: Int) = localDataSource.getById(id)

    suspend fun getAllCountries() = localDataSource.getAll()

    suspend fun saveCountry(country: CountryEntity) = localDataSource.insert(country)
}