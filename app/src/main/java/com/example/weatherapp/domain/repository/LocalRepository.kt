package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun insert(weatherData: WeatherResponse)

    fun update(weatherData: WeatherResponse)

    fun getAllData(): Flow<List<WeatherResponse>>
}