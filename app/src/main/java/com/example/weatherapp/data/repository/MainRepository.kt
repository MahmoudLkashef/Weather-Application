package com.example.weatherapp.data.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.model.WeatherResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val context: Context){
    val database=WeatherDatabase.getDatabase(context)
    val remoteRepository=RemoteRepositoryImp()
    val localRepository=LocalRepositoryImp(database)
    fun saveResponseToDatabase(response: Response)
    {
       val responseMutableLiveData= remoteRepository.getWeatherData("cairo")
        //responseMapper(responseMutableLiveData.value!!)
        responseMapper(response)

    }

    private fun responseMapper(response: Response)
    {
        //var weatherResponse:WeatherResponse

        var city =response.city.name
        var country=response.city.country

        for(item in response.list)
        {
            var date=item.dt_txt_date
            var icon=item.weather.get(0).icon
            var cloudiness=item.clouds.all
            var time=item.dt_txt_time
            var temp=item.main.temp
            var description=item.weather.get(0).description
            var windSpeed=item.wind.speed
            var pressure=item.main.pressure
            var humidity=item.main.humidity
            var weatherResponse=WeatherResponse(
                city = city,
                country = country,
                date = date,
                icon = icon,
                cloudiness = cloudiness,
                temp = temp,
                time = time,
                description = description,
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity
            )
            localRepository.insert(weatherResponse)
        }
    }
}