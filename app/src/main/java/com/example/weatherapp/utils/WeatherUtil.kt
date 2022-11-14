package com.example.weatherapp.utils

import android.util.Log
import android.widget.ImageView
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.model.WeatherResponse
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherUtil {
    companion object{
        fun getCurrentDayWeather(list: List<WeatherData>,date:String):List<WeatherData>
        {
            var currentDayList=ArrayList<WeatherData>()
            for (weatherData in list)
            {
                val weatherDataDate=weatherData.dt_txt.split(" ").get(0)
                if(weatherDataDate.equals(date))currentDayList.add(weatherData)
            }
            return currentDayList
        }

        fun getNextFiveDaysWeather(list: List<WeatherResponse>):List<WeatherResponse>
        {
            Log.d("utill",list.size.toString())
            val currentDate=list.get(0).date
            var map= mutableMapOf<String, WeatherResponse>()
            for(i in list.indices)
            {
                var date=list.get(i).date
                if(!map.containsKey(date)) map.put(date,list.get(i))
            }
            map.remove(currentDate)
            return map.values.toList()
        }

        fun convertDateToDay(date:String):String
        {
            val firstDate = SimpleDateFormat("dd-MM-yyyy").parse(date)
            val day= SimpleDateFormat("EEEE").format(firstDate)
            return day
        }

        private fun getCurrentDateTime(): Date {
            Log.d("utill",Calendar.getInstance().time.toString())
            return Calendar.getInstance().time
        }

        fun getCurrentTime():String
        {
            return SimpleDateFormat("hh:mm").format(getCurrentDateTime()).toString()

        }
        fun loadWeatherIcon(icon:String,image:ImageView)
        {
            Picasso.get().load(StringBuilder("http://openweathermap.org/img/wn/")
                .append(icon)
                .append("@2x.png").toString()).into(image)
        }
        fun getCurrentDate():String
        {
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val current = formatter.format(time)
            return current
        }
    }

}