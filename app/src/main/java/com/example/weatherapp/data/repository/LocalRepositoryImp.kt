package com.example.weatherapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(private val database: WeatherDatabase) :LocalRepository{

    override suspend fun insert(weatherData: WeatherResponse) {
        database.weatherDao.insert(weatherData)
    }

    override suspend fun insertWeatherResponseList(responseList: List<WeatherResponse>) {
        database.weatherDao.insertWeatherResponseList(responseList)
    }

    override suspend fun update(weatherData: WeatherResponse) {
        database.weatherDao.update(weatherData)
    }

    override suspend fun getAllData(): List<WeatherResponse> {
        return database.weatherDao.getAllData()
    }

    override suspend fun getCurrentWeatherData(date: String): List<WeatherResponse> {
        return database.weatherDao.getCurrentWeatherData(date)
    }
}