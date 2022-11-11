package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.network.WeatherAPI
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val weatherApi: WeatherAPI
) : RemoteRepository {

    private val TAG = "RemoteRepository"
    override suspend fun getWeatherData(city: String): Response? = withContext(Dispatchers.IO)
    {
        var response=weatherApi.getCityWeather(city,BuildConfig.API_KEY,"metric")
        Log.d(TAG,response.body()?.list?.size.toString())
        response.body()
    }
}