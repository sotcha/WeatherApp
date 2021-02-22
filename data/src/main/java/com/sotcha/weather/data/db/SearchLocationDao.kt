package com.sotcha.weather.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sotcha.weather.data.model.entity.SearchLocationEntity

/**
 * Data Access Object for the [com.sotcha.weather.data.model.entity.SearchLocationEntity] entity
 */
@Dao
interface SearchLocationDao {
    @Query("SELECT * FROM location")
    fun getAll(): List<SearchLocationEntity>

    @Insert
    fun insert(location: SearchLocationEntity): Long

    @Delete
    fun delete(location: SearchLocationEntity): Int
}



