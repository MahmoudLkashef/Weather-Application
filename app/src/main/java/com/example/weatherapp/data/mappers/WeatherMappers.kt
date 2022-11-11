package com.example.weatherapp.data.mappers

import android.util.Log
import com.example.weatherapp.domain.model.Response
import com.example.weatherapp.domain.model.WeatherResponse

class WeatherMappers {

    companion object {
        fun convertResponseToWeatherResponse(response: Response): List<WeatherResponse> {

            var weatherResponseList = ArrayList<WeatherResponse>(arrayListOf())

            var city = response.city.name
            var country = response.city.country
            for (item in response.list) {
                var date = item.dt_txt_date
                var icon = item.weather.get(0).icon
                var cloudiness = item.clouds.all
                var time = item.dt_txt_time
                var temp = item.main.temp
                var description = item.weather.get(0).description
                var windSpeed = item.wind.speed
                var pressure = item.main.pressure
                var humidity = item.main.humidity
                var weatherResponse = WeatherResponse(
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
                weatherResponseList.add(weatherResponse)
            }
            Log.d("mapperr",response.list.size.toString())
            return weatherResponseList
        }
    }
}