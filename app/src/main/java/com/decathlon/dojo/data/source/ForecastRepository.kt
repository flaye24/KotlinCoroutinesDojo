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

    override suspend fun getDailyForecasts(): List<DailyForecast> = coroutineScope {
        val localDailyForecastsDeferred = async(Dispatchers.IO) {
            forecastLocalDataSource.getDailyForecastsSync()
        }

        val localDailyForecasts = localDailyForecastsDeferred.await()

        if (localDailyForecasts.isEmpty() || cacheIsDirty) {
            val remoteDailyForecastDeferred = async(Dispatchers.IO) {
                getAndSaveRemoteDailyForecasts()
            }
            return@coroutineScope remoteDailyForecastDeferred.await()
        }

        return@coroutineScope localDailyForecasts
    }

    override fun invalidateForecastsCache() {
        cacheIsDirty = true

    }

    private fun getAndSaveRemoteDailyForecasts(): List<DailyForecast> {
        val remoteDailyForecasts = forecastRemoteDataSource.getDailyForecastsSync()
        forecastLocalDataSource.saveDailyForecastsSync(remoteDailyForecasts)
        cacheIsDirty = false
        return remoteDailyForecasts
    }
}