package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.data.repository.MainRepository
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.utils.WeatherUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val TAG = "ViewModel"

    private val _weatherData = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val weatherData get() = _weatherData


    suspend fun getWeatherData(city:String) {
        mainRepository.getDataFromApi(city)
        _weatherData.emit(mainRepository.getWeatherDataFromDatabase())
        Log.d(TAG,_weatherData.value.toString())
    }

    suspend fun getCurrentWeatherData():List<WeatherResponse>
    {
        return mainRepository.getCurrentWeatherData(WeatherUtil.getCurrentDate())
    }

    suspend fun getNextFiveDaysData():List<WeatherResponse>
    {
        return WeatherUtil.getNextFiveDaysWeather(mainRepository.getWeatherDataFromDatabase())
    }

    fun getDailyForecastData(date: String) {

    }
}