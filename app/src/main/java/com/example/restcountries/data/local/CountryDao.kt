package com.example.restcountries.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.restcountries.data.entities.CountryEntity

import java.util.List;

import kotlinx.coroutines.flow.Flow;


@Dao
interface CountryDao {
    @Query("SELECT * FROM country WHERE id = :id")
    fun getById(id: Int): Flow<CountryEntity>

    @Query("SELECT * FROM country")
    fun getAll(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lights: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lights: List<CountryEntity>)

    @Query("DELETE FROM country")
    suspend fun deleteAll()
}