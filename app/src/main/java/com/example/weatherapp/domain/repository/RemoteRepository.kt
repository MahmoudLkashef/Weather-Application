package com.example.weatherapp.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.model.Response

interface RemoteRepository {

    fun getWeatherData(city:String): MutableLiveData<Response>
}