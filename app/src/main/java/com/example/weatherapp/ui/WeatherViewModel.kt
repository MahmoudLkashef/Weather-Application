package com.example.weatherapp.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.data.network.WeatherAPI
import com.example.weatherapp.data.network.WeatherClient
import com.example.weatherapp.data.repository.MainRepository
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.WeatherUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val TAG = "ViewModel"


    private val _response = MutableLiveData<Response>()
    val response: LiveData<Response> get() = _response


    private val _dailyData = MutableLiveData<List<WeatherData>>()
    val dailyData: MutableLiveData<List<WeatherData>> get() = _dailyData


    private val _weatherData = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val weatherData get() = _weatherData

    lateinit var cityName: String

    suspend fun getWeatherData(city:String) {
        mainRepository.getDataFromApi(city)
        _weatherData.emit(mainRepository.getWeatherDataFromDatabase())
        Log.d(TAG,_weatherData.value.toString())
    }

    fun getDailyForecastData(date: String) {
        var dailyList = WeatherUtil.getCurrentDayWeather(_response.value!!.list, date)
        _dailyData.value = dailyList
    }
}