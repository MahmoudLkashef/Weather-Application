package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImp(private val database: WeatherDatabase) :LocalRepository{
    override fun insert(weatherData: WeatherResponse) {
        database.weatherDao.insert(weatherData)
    }

    override fun update(weatherData: WeatherResponse) {
        database.weatherDao.update(weatherData)
    }

    override fun getAllData(): Flow<List<WeatherResponse>> {
        return database.weatherDao.getAllData()
    }
}