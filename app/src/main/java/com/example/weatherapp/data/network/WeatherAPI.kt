package com.example.weatherapp.data.network

import com.example.weatherapp.domain.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/forecast")
    fun getCityWeather(@Query("q")cityName:String,
                       @Query("appid")apiKey:String,
                       @Query("units")unit:String):Call<Response>

}