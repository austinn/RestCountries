package com.example.restcountries.data.repositories

import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.data.local.daos.CountryDao
import com.example.restcountries.data.remote.ApiResult
import com.example.restcountries.data.remote.services.CountryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val localDataSource: CountryDao,
    private val countryService: CountryService,
) {

    suspend fun getCountryListCacheFirst(): Flow<List<CountryEntity>> {
        return localDataSource.getAll()
            .onEach { countries ->
                if (countries.isEmpty()) {
                    Timber.i("No countries in cache")
                    refreshCountries()
                }
            }
    }

    private suspend fun refreshCountries(): Flow<List<CountryEntity>> {

        return flow {
            val response = countryService.getCountryList()
            Timber.i("${response.body()}")

            if (response.isSuccessful) {
                Timber.i("response.isSuccessful ${response.body()?.size}")
                val countries = response.body()?.map {
                    CountryEntity(name = it.name.common)
                }

                countries?.let {
                    localDataSource.insertAll(it)
                    emit(it)

                }
            } else {
                Timber.e("Not successful")
            }
        }
    }


    suspend fun getCountryList(): Flow<List<CountryEntity>> {
        Timber.i("getCountryList() - fetching from Network")

        return flow {
            val response = countryService.getCountryList()

            if (response.isSuccessful) {
                Timber.i("response.isSuccessful")
                emit(ApiResult.success(data = response.body()))
            } else {
                emit(ApiResult.error(message = "Something went wrong"))
            }

        }.map {
            if (it.data != null) {
                it.data.map { country ->
                    CountryEntity(
                        name = country.name.common,
                        flag = country.flags?.png,
                    )
                }
            } else {
                emptyList()
            }
        }.map {
            it.sortedBy { e -> e.name }
        }

    }

    suspend fun getCountryDetails(name: String): Flow<CountryEntity?> {
        return flow {
            val response = countryService.getCountryDetails(name)

            if (response.isSuccessful) {
                Timber.i("response.isSuccessful")
                emit(ApiResult.success(data = response.body()))
            } else {
                emit(ApiResult.error(message = "Something went wrong"))
            }
        }.map {
            it.data?.let { details ->
                // assume a match was found, since we're passing in the full name
                val country = details.first()
                CountryEntity(
                    name = country.name.common,
                    capital = country.capital?.firstOrNull(),
                    population = country.population,
                    area = country.area,
                    region = country.region,
                    subregion = country.subregion,
                )
            }
        }
    }
}