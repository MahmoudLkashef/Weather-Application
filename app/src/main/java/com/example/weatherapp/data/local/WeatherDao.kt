package com.example.weatherapp.data.local

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherData:WeatherResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherResponseList(responseList:List<WeatherResponse>)

    @Update
    suspend fun update(weatherData:WeatherResponse)

    @Query("select * from weather_table")
    suspend fun getAllData():List<WeatherResponse>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()


}