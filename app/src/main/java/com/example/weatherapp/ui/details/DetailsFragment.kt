package com.example.weatherapp.ui.details

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
import com.example.weatherapp.ui.adapter.HourlyWeatherAdapter
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.utils.WeatherUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    private val viewModel: WeatherViewModel by activityViewModels()
    lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        hourlyWeatherAdapter = HourlyWeatherAdapter()
        binding.rvHourlyWeatherDetails.layoutManager= LinearLayoutManager(context)
        binding.rvHourlyWeatherDetails.adapter = hourlyWeatherAdapter

        binding.imgBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment)
        }
/*        viewModel.dailyData.observe(viewLifecycleOwner,
            Observer { list ->

                var currentDate = list.get(0).dt_txt_date

                binding.response = list[0]

                hourlyWeatherAdapter.submitList(WeatherUtil.getCurrentDayWeather(list, currentDate))

                WeatherUtil.loadWeatherIcon(list.get(0).weather.get(0).icon,binding.imgDetailsWeather)

            })*/
        GlobalScope.launch {
            viewModel
        }

    }


}