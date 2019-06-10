package com.decathlon.dojo.data.model

import com.google.gson.annotations.SerializedName

data class TemperatureDTO(
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double
)