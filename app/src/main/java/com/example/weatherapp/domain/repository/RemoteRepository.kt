package com.example.weatherapp.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.model.Response

interface RemoteRepository {

    suspend fun getWeatherData(city:String): Response?
}