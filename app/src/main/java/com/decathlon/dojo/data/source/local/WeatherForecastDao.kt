package com.decathlon.dojo.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.model.TABLE_DAILY_FORECAST

@Dao
abstract class WeatherForecastDao {

    //TODO : 6 - Room now has coroutines support, convert to suspend functions

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertDailyForecasts(dailyForecasts: List<DailyForecast>)

    @Query("SELECT * FROM $TABLE_DAILY_FORECAST")
    abstract suspend fun getDailyForecasts(): List<DailyForecast>

    @Query("DELETE FROM $TABLE_DAILY_FORECAST")
    abstract suspend fun deleteAllDailyForecasts()

}