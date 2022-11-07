package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Response
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.network.WeatherAPI
import com.example.weatherapp.network.WeatherClient
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.WeatherUtil
import retrofit2.Call
import retrofit2.Callback

class WeatherViewModel : ViewModel() {
    private val TAG = "ViewModel"


    private val _response = MutableLiveData<Response>()
    val response: LiveData<Response> get() = _response


    private val _dailyData = MutableLiveData<List<WeatherData>>()
    val dailyData: MutableLiveData<List<WeatherData>> get() = _dailyData

    lateinit var  cityName: String

    fun getWeatherData(city:String) {
        cityName = city
        val retrofit = WeatherClient.getInstance().create(WeatherAPI::class.java)
        val result = retrofit.getCityWeather(city, Constants.API_KEY, "metric")
        result.enqueue(object : Callback<Response?> {
            override fun onResponse(
                call: Call<Response?>,
                response: retrofit2.Response<Response?>
            ) {

                if (response.isSuccessful)
                    _response.value = response.body()
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }


        })
    }

fun getDailyForecastData(date:String ){
     var dailyList = WeatherUtil.getCurrentDayWeather(_response.value!!.list,date)
    _dailyData.value =dailyList


}
}