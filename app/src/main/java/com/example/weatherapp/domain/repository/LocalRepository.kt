package com.example.weatherapp.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LocalRepository {

    suspend fun insert(weatherData: WeatherResponse)

    suspend fun insertWeatherResponseList(responseList:List<WeatherResponse>)

    suspend fun update(weatherData: WeatherResponse)

    suspend fun getAllData(): List<WeatherResponse>

    suspend fun getCurrentWeatherData(date:String):List<WeatherResponse>
}