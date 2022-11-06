package com.example.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HourlyWeatherListItemBinding
import com.example.weatherapp.model.WeatherData
import com.squareup.picasso.Picasso

class HourlyWeatherAdapter : ListAdapter<WeatherData, HourlyWeatherAdapter.HourlyWeatherViewHolder>(
    DiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val view =
            LayoutInflater.from(parent.context)
        val listItemBinding=HourlyWeatherListItemBinding.inflate(view,parent,false)
        return HourlyWeatherViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(item)
    }

    class HourlyWeatherViewHolder(private val hourly_weather_list_item: HourlyWeatherListItemBinding)
        : RecyclerView.ViewHolder(hourly_weather_list_item.root) {

            fun bind(item:WeatherData)
            {
                hourly_weather_list_item.weatherData=item

                Picasso.get().load(StringBuilder("http://openweathermap.org/img/wn/")
                    .append(item.weather.get(0).icon)
                    .append("@2x.png").toString()).into(hourly_weather_list_item.imgCloudListItem)
            }
    }

}

class DiffCallback : DiffUtil.ItemCallback<WeatherData>() {
    override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
        return oldItem?.dt==newItem?.dt
    }

    override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
        return oldItem==newItem
    }

}