package com.decathlon.dojo.data.source

import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.local.ForecastLocalDataSource
import com.decathlon.dojo.data.source.remote.ForecastRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ForecastRepository @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource
) : ForecastDataSource {

    private var cacheIsDirty = false

    override suspend fun getDailyForecasts(): List<DailyForecast> {

        val localDailyForecasts = forecastLocalDataSource.getDailyForecasts()

        if (localDailyForecasts.isEmpty() || cacheIsDirty) {

            return getAndSaveRemoteDailyForecasts()
        }

        return localDailyForecasts
    }

    override fun invalidateForecastsCache() {
        cacheIsDirty = true

    }

    private suspend fun getAndSaveRemoteDailyForecasts(): List<DailyForecast> {
        val remoteDailyForecasts = forecastRemoteDataSource.getDailyForecasts()
        forecastLocalDataSource.saveDailyForecasts(remoteDailyForecasts)
        cacheIsDirty = false
        return remoteDailyForecasts
    }
}