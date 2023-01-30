package com.example.restcountries.hilt

import com.example.restcountries.data.local.CountryDao
import com.example.restcountries.data.repositories.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton

    fun provideCountryRepositoryRepository(
        countryDao: CountryDao,
    ) = CountryRepository(countryDao)

}