package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.mappers.WeatherMappers
import com.example.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private val TAG="MainRepository"

class MainRepository @Inject constructor(
    private val remoteRepository: RemoteRepositoryImp,
    private val localRepository: LocalRepositoryImp
) {

    suspend fun getDataFromApi(city: String) {
        val response = remoteRepository.getWeatherData(city)
        val responseList = WeatherMappers.convertResponseToWeatherResponse(response!!)
        localRepository.insertWeatherResponseList(responseList)
    }

    suspend fun getWeatherDataFromDatabase():List<WeatherResponse>{
        return localRepository.getAllData()
    }

    suspend fun getCurrentWeatherData(date:String):List<WeatherResponse>
    {
        return localRepository.getCurrentWeatherData(date)
    }
}