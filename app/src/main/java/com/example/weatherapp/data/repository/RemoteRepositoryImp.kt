package com.example.weatherapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.network.WeatherAPI
import com.example.weatherapp.data.network.WeatherClient
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback

class RemoteRepositoryImp():RemoteRepository {

    private val TAG="RemoteRepository"



    override fun getWeatherData(city: String): MutableLiveData<Response> {
        var responseMutableLiveData = MutableLiveData<Response>()
        val retrofit = WeatherClient.getInstance().create(WeatherAPI::class.java)
        val result=retrofit.getCityWeather(city,BuildConfig.API_KEY,"metric")
        result.enqueue(object : Callback<Response?> {
            override fun onResponse(
                call: Call<Response?>,
                response: retrofit2.Response<Response?>
            ) {

                if(response.isSuccessful){
                    responseMutableLiveData.value=response.body()
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }
        })
        return responseMutableLiveData

    }
}