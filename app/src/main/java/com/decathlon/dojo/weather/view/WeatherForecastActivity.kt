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
import com.decathlon.dojo.weather.viewmodel.ForecastViewModel
import com.decathlon.dojo.weather.viewmodel.WeatherViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherForecastActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewDataBinding: ActivityWeatherForecastBinding

    private lateinit var forecastAdapter: ForecastAdapter

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast)

        initWeatherForecastList()

        forecastViewModel = ViewModelProviders.of(this, weatherViewModelFactory).get(ForecastViewModel::class.java)
        forecastViewModel.loadWeatherForecast()

        initViewModelObservers()
        initSwipeToRefresh()

        viewDataBinding.srlForecast.isRefreshing = true

    }

    private fun initViewModelObservers() {
        forecastViewModel.weatherForecasts.observe(this, Observer { dailyForecasts ->
            forecastAdapter.replaceData(dailyForecasts)
            viewDataBinding.srlForecast.isRefreshing = false
        })
        forecastViewModel.displayErrorMessage.observe(this, Observer { errorMessage ->
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
        viewDataBinding.srlForecast.isRefreshing = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onRefresh() {
        forecastViewModel.onRefresh()
        viewDataBinding.srlForecast.isRefreshing = false
    }


}
