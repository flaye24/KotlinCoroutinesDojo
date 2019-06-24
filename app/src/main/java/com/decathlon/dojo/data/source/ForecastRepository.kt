package com.decathlon.dojo.data.source

import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.local.ForecastLocalDataSource
import com.decathlon.dojo.data.source.remote.ForecastRemoteDataSource
import com.google.android.gms.location.LocationServices
import io.reactivex.Single
import javax.inject.Inject


class ForecastRepository @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource
) : ForecastDataSource {

    private var cacheIsDirty = false

    //TODO : 11 - Convert implementation

    override fun getDailyForecasts(): Single<List<DailyForecast>> {
        val remoteDailyForecasts = getAndSaveRemoteDailyForecasts()

        // Respond immediately with remote daily forecasts if cache is dirty
        return if (cacheIsDirty) {
            remoteDailyForecasts
        } else {
            // Query the local storage if available. If not, query the network.
            val localDailyForecasts = forecastLocalDataSource.getDailyForecasts()
            Single.concat<List<DailyForecast>>(localDailyForecasts, remoteDailyForecasts)
                .filter { dailyForecast ->
                    dailyForecast.isNotEmpty()
                }
                .firstOrError()
        }
    }

    override fun invalidateForecastsCache() {
        cacheIsDirty = true

    }

    private fun getAndSaveRemoteDailyForecasts(): Single<List<DailyForecast>> {

        return forecastRemoteDataSource
            .getDailyForecasts()
            .flatMap { dailyForecasts ->
                forecastLocalDataSource.saveDailyForecasts(dailyForecasts)
                    .andThen(Single.just(dailyForecasts))
            }.doOnSuccess {
                cacheIsDirty = false
            }

    }
}