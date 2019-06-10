package com.decathlon.dojo.data.source

import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.local.WeatherForecastLocalDataSource
import com.decathlon.dojo.data.source.remote.WeatherForecastRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject


class WeatherForecastRepository @Inject constructor(
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource,
    private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource
) : WeatherForecastDataSource {

    private var cacheIsDirty = false

    override fun getDailyForecasts(): Single<List<DailyForecast>> {
        val remoteDailyForecasts = getAndSaveRemoteDailyForecasts()

        // Respond immediately with remote daily forecasts if cache is dirty
        return if (cacheIsDirty) {
            remoteDailyForecasts
        } else {
            // Query the local storage if available. If not, query the network.
            val localDailyForecasts = weatherForecastLocalDataSource.getDailyForecasts()
            Single.concat<List<DailyForecast>>(localDailyForecasts, remoteDailyForecasts)
                .filter { dailyForecast ->
                    dailyForecast.isNotEmpty()
                }
                .firstOrError()
        }
    }

    override fun refreshDailyForecasts() {
        cacheIsDirty = true

    }

    private fun getAndSaveRemoteDailyForecasts(): Single<List<DailyForecast>> {
        return weatherForecastRemoteDataSource
            .getDailyForecasts()
            .flatMap { dailyForecasts ->
                weatherForecastLocalDataSource.saveDailyForecasts(dailyForecasts)
                    .andThen(Single.just(dailyForecasts))
            }.doOnSuccess {
                cacheIsDirty = false
            }
    }
}