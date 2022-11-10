package com.example.weatherapp.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.DailyForecastListItemBinding
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.model.WeatherResponse
import com.squareup.picasso.Picasso

class DailyForecastAdapter:ListAdapter<WeatherResponse, DailyForecastAdapter.DailyForecastViewHolder>(DiffCallback2())
{
    var onitemClicked:((WeatherResponse)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context)
        val listItemBinding=DailyForecastListItemBinding.inflate(view,parent,false)
        return DailyForecastViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener(View.OnClickListener {
            onitemClicked?.invoke(item)
        })
    }


    class DailyForecastViewHolder (private val daily_forecast_list_item: DailyForecastListItemBinding)
        : RecyclerView.ViewHolder(daily_forecast_list_item.root)
    {
            fun bind(item:WeatherResponse)
            {
                daily_forecast_list_item.weatherData=item

                Picasso.get().load(StringBuilder("http://openweathermap.org/img/wn/")
                    .append(item.icon)
                    .append("@2x.png").toString())
                    .into(daily_forecast_list_item.imgCloudDailyForecast)
            }
    }


}

class DiffCallback2 : DiffUtil.ItemCallback<WeatherResponse>() {
    override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
        return oldItem?.id==newItem?.id
    }

    override fun areContentsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
        return oldItem==newItem
    }
}

