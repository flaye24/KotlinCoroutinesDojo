package com.decathlon.dojo.weather.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.decathlon.dojo.R
import com.decathlon.dojo.databinding.ActivityWeatherForecastBinding
import com.decathlon.dojo.weather.ForecastAdapter
import com.decathlon.dojo.weather.viewmodel.WeatherForecastViewModel
import com.decathlon.dojo.weather.viewmodel.WeatherViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherForecastActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewDataBinding: ActivityWeatherForecastBinding

    private lateinit var forecastAdapter: ForecastAdapter

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private lateinit var weatherForecastViewModel: WeatherForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast)

        initWeatherForecastList()

        weatherForecastViewModel = ViewModelProviders.of(this, weatherViewModelFactory).get(WeatherForecastViewModel::class.java)
        weatherForecastViewModel.loadWeatherForecast()

        initViewModelObservers()
        initSwipeToRefresh()

    }

    private fun initViewModelObservers() {
        weatherForecastViewModel.weatherForecasts.observe(this, Observer { dailyForecasts ->
            forecastAdapter.replaceData(dailyForecasts)
        })
        weatherForecastViewModel.displayErrorMessage.observe(this, Observer { errorMessage ->
            displayErrorMessage(errorMessage)
        })
    }

    private fun initSwipeToRefresh() {
        viewDataBinding.srlForecast.setOnRefreshListener(this)
    }

    private fun initWeatherForecastList() {
        forecastAdapter = ForecastAdapter(ArrayList())
        viewDataBinding.rvForecast.adapter = forecastAdapter
    }

    private fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onRefresh() {
        weatherForecastViewModel.onRefresh()
        viewDataBinding.srlForecast.isRefreshing = false
    }


}
