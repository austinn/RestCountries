package com.example.restcountries.hilt

import android.content.Context
import com.example.restcountries.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideCountryDao(db: AppDatabase) = db.countryDao()
}