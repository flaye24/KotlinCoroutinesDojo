package com.decathlon.dojo.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.model.TABLE_DAILY_FORECAST
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class WeatherForecastDao {

    //TODO : 6 - Room now has coroutines support, convert to suspend functions

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDailyForecasts(dailyForecasts: List<DailyForecast>): Completable

    @Query("SELECT * FROM $TABLE_DAILY_FORECAST")
    abstract fun getDailyForecasts(): Single<List<DailyForecast>>

    @Query("DELETE FROM $TABLE_DAILY_FORECAST")
    abstract fun deleteAllDailyForecasts(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDailyForecastsSync(dailyForecasts: List<DailyForecast>)

    @Query("SELECT * FROM $TABLE_DAILY_FORECAST")
    abstract fun getDailyForecastsSync(): List<DailyForecast>

    @Query("DELETE FROM $TABLE_DAILY_FORECAST")
    abstract fun deleteAllDailyForecastsSync()

}