package com.example.weatherapp.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.weatherapp.utils.WeatherUtil

@Entity(tableName = "weather_table", indices = arrayOf(Index(value = ["date","time"], unique = true)))
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var city: String,
    var country: String,
    var temp: Double,
    var pressure: Double,
    var humidity: Double,
    var description: String,
    var icon: String,
    var windSpeed: Double,
    var cloudiness: Int,
    var date: String,
    var time: String
) {
        val day: String
        get() = WeatherUtil.convertDateToDay(date)
}
