package com.decathlon.dojo.data.model

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("id") val weatherId: Int,
    @SerializedName("main") val weatherTitle: String
)