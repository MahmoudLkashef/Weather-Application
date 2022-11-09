package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.DailyForecastAdapter
import com.example.weatherapp.adapter.HourlyWeatherAdapter
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.utils.WeatherUtil
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    val TAG="HomeFragment"
    lateinit var binding:FragmentHomeBinding
     val viewModel: WeatherViewModel by activityViewModels()
    lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter
    lateinit var dailyForecastAdapter: DailyForecastAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDailyWeather.layoutManager=LinearLayoutManager(context)

        hourlyWeatherAdapter= HourlyWeatherAdapter()
        dailyForecastAdapter= DailyForecastAdapter()

        binding.rvHourlyWeather.adapter=hourlyWeatherAdapter
        binding.rvDailyWeather.adapter=dailyForecastAdapter

        viewModel.getWeatherData("Cairo")


        dailyForecastAdapter.onitemClicked=
        {

            viewModel.getDailyForecastData(it.dt_txt_date)
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailsFragment)
        }

        viewModel.response.observe(viewLifecycleOwner, Observer {

            response->
            var currentDate=response.list.get(0).dt_txt_date

            hourlyWeatherAdapter.submitList(WeatherUtil.getCurrentDayWeather(response.list,currentDate))

            dailyForecastAdapter.submitList(WeatherUtil.getNextFiveDaysWeather(response.list))

            binding.response=response

            WeatherUtil.loadWeatherIcon(response.list.get(0).weather.get(0).icon,binding.imgCurrentWeather)

        })
    }

}