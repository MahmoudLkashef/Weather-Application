package com.example.weatherapp.model

import android.os.Parcelable
import com.example.weatherapp.utils.WeatherUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(var list: List<WeatherData>,var city:City): Parcelable

@Parcelize
data class City(var name:String,var coord:Coord,var country:String , var timezone:Int): Parcelable

@Parcelize
data class Coord(var lat:Double,var lon:Double): Parcelable

@Parcelize
data class WeatherData(val dt:Int,val main: Main,val weather:List<Weather>,val wind:Wind
,val dt_txt:String) : Parcelable
{
    val dt_txt_date:String
    get()=dt_txt.split(" ").get(0)

    val dt_txt_day:String
    get() = WeatherUtil.convertDateToDay(dt_txt.split(" ").get(0))

    val dt_txt_time:String
        get()=dt_txt.split(" ").get(1)
}

@Parcelize
data class Main(val temp:Double ,val pressure:Double,val humidity:Double): Parcelable
{
    val tempString:String
    get() = temp.toString()+"°C"
}

@Parcelize
data class Weather(val id:Int,val main:String,val description:String,val icon:String): Parcelable

@Parcelize
data class Wind(val speed:Double,val deg:Double,val gust:Double): Parcelable

