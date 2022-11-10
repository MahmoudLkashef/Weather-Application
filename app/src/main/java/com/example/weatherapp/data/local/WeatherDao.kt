package com.example.weatherapp.data.local

import androidx.room.*
import com.example.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData:WeatherResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherResponseList(responseList:List<WeatherResponse>)

    @Update
    fun update(weatherData:WeatherResponse)

    @Query("select * from weather_table")
    fun getAllData():Flow<List<WeatherResponse>>

    @Query("DELETE FROM weather_table")
    fun deleteAll()


}