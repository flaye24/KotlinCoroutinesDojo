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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDailyForecasts(dailyForecasts: List<DailyForecast>): Completable

    @Query("SELECT * FROM $TABLE_DAILY_FORECAST")
    abstract fun getDailyForecasts(): Single<List<DailyForecast>>

    @Query("DELETE FROM $TABLE_DAILY_FORECAST")
    abstract fun deleteAllDailyForecasts(): Completable

}