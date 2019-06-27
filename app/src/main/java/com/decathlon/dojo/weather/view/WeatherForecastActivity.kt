package com.decathlon.dojo.weather.view

import android.Manifest
import android.annotation.SuppressLint
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
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

const val LOCATION_PERMISSIONS_REQUEST_CODE = 42

class WeatherForecastActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewDataBinding: ActivityWeatherForecastBinding

    private lateinit var forecastAdapter: ForecastAdapter

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private lateinit var weatherForecastViewModel: WeatherForecastViewModel


    private val LOCATION_PERMISSIONS =
        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast)

        initWeatherForecastList()

        weatherForecastViewModel =
            ViewModelProviders.of(this, weatherViewModelFactory).get(WeatherForecastViewModel::class.java)

        initViewModelObservers()
        initSwipeToRefresh()

        viewDataBinding.srlForecast.isRefreshing = true

        if (EasyPermissions.hasPermissions(this, *LOCATION_PERMISSIONS)) {
            loadWeatherForecast()
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.permission_rationale),
                LOCATION_PERMISSIONS_REQUEST_CODE,
                *LOCATION_PERMISSIONS
            )
        }

    }

    private fun initViewModelObservers() {
        weatherForecastViewModel.weatherForecasts.observe(this, Observer { dailyForecasts ->
            forecastAdapter.replaceData(dailyForecasts)
            viewDataBinding.srlForecast.isRefreshing = false
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(LOCATION_PERMISSIONS_REQUEST_CODE)
    @SuppressLint("MissingPermission")
    private fun loadWeatherForecast() {
        weatherForecastViewModel.loadWeatherForecast()
    }
}
