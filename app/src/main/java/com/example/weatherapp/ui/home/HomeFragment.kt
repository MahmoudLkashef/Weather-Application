package com.example.weatherapp.ui.home

import android.os.Bundle
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
import com.example.weatherapp.ui.adapter.DailyForecastAdapter
import com.example.weatherapp.ui.adapter.HourlyWeatherAdapter
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.utils.WeatherUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
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
        

        dailyForecastAdapter.onitemClicked=
        {

            viewModel.getDailyForecastData(it.dt_txt_date)
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailsFragment)
        }

        GlobalScope.launch {
            viewModel.getWeatherData("london")
            viewModel.weatherData.collect{
                withContext(Dispatchers.Main)
                {
                    binding.tvCurrentWeatherDescription.text=it.get(0).description
                }

            }
        }
    }

}