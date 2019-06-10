package com.decathlon.dojo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_DAILY_FORECAST = "daily_forecast"
const val COLUMN_DATE = "date"
const val COLUMN_LOWEST_TEMP = "lowest_temp"
const val COLUMN_HIGHEST_TEMP = "highest_temp"
const val COLUMN_DESCRIPTION = "weather_description"
const val COLUMN_WEATHER_ICON_RES_ID = "weather_res_id"

@Entity(tableName = TABLE_DAILY_FORECAST)
data class DailyForecast(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_DATE)
    val date: String,

    @ColumnInfo(name = COLUMN_LOWEST_TEMP)
    val lowestTemp: String,

    @ColumnInfo(name = COLUMN_HIGHEST_TEMP)
    val highestTemp: String,

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val weatherDescription: String,

    @ColumnInfo(name = COLUMN_WEATHER_ICON_RES_ID)
    val weatherResId: Int
)