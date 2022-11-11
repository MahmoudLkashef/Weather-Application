package com.example.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.domain.model.WeatherResponse

@Database(entities = arrayOf(WeatherResponse::class), version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao

}