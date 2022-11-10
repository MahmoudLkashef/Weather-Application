package com.example.weatherapp.data.repository

import android.content.Context
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.data.mappers.WeatherMappers
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.model.WeatherResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val context: Context){
    val database=WeatherDatabase.getDatabase(context)
    val remoteRepository=RemoteRepositoryImp()
    val localRepository=LocalRepositoryImp(database)
    fun saveResponseToDatabase(response: Response)
    {
       //val responseMutableLiveData= remoteRepository.getWeatherData("cairo")
        //responseMapper(responseMutableLiveData.value!!)
        val responseList=WeatherMappers.convertResponseToWeatherResponse(response)
        localRepository.insertWeatherResponseList(responseList)
    }

}