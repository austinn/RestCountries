package com.example.restcountries.hilt

import com.example.restcountries.data.local.daos.CountryDao
import com.example.restcountries.data.remote.services.CountryService
import com.example.restcountries.data.repositories.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideCountryService(retrofit: Retrofit): CountryService = retrofit.create()

    @Provides
    @Singleton
    fun provideCountryRepositoryRepository(
        countryDao: CountryDao,
        countryService: CountryService,
    ) = CountryRepository(countryDao, countryService)

}