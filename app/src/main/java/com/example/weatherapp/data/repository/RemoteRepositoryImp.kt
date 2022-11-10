package com.example.weatherapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.network.WeatherAPI
import com.example.weatherapp.data.network.WeatherClient
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val weatherApi: WeatherAPI
) : RemoteRepository {

    private val TAG = "RemoteRepository"
    override suspend fun getWeatherData(city: String): Response? = withContext(Dispatchers.IO)
    {
        var response=weatherApi.getCityWeather(city,BuildConfig.API_KEY,"metric")
        Log.d(TAG,response.body().toString())
        response.body()
    }
}