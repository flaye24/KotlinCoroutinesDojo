package com.decathlon.dojo.utils

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LocationManager @Inject constructor(application: Application) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    //TODO 1 : make getLastKnowLocation a suspend function
    //TODO 2 : use suspendCoroutine to convert callback to coroutines
    @SuppressLint("MissingPermission")
    fun getLastKnowLocation(block: (Result<Location>) -> Unit) {
        fusedLocationClient.lastLocation.addOnCompleteListener { locationTask ->
            if (locationTask.isSuccessful) {
                locationTask.result?.let { location ->
                    block(Result.success(location))
                } ?: run {
                    block(Result.failure(NullPointerException("Location is null")))
                }
            } else {
                block(Result.failure(locationTask.exception!!))
            }
        }
    }
}