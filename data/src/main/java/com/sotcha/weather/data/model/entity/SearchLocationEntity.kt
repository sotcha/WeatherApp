package com.sotcha.weather.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity to store a search location
 */
@Entity(tableName = "location")
data class SearchLocationEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "lat") val lat: Float,
    @ColumnInfo(name = "lng") val lng: Float
)
