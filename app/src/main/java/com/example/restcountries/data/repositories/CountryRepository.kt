package com.example.restcountries.data.repositories

import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.data.local.daos.CountryDao
import com.example.restcountries.data.remote.ApiResult
import com.example.restcountries.data.remote.services.CountryService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val localDataSource: CountryDao,
    private val countryService: CountryService,
) {

    suspend fun getCountryList(): Flow<List<CountryEntity>> {
        Timber.i("getCountryList() - fetching from Network")

        return flow {
            emit(ApiResult.loading())

            // add an artificial delay for testing
            delay(2000L)

            val response = countryService.getCountryList()

            if (response.isSuccessful) {
                emit(ApiResult.success(data = response.body()))
            } else {
                emit(ApiResult.error(message = "Something went wrong"))
            }

        }.map {
            if (it.data != null) {
                it.data.map { e -> CountryEntity(name = e.name.common) }
            } else {
                emptyList()
            }
        }

    }
}