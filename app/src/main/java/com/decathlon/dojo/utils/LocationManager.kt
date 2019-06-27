package com.decathlon.dojo.utils

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocationManager @Inject constructor(application: Application) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    //TODO 1 : make getLastKnowLocation a suspend function
    //TODO 2 : use suspendCoroutine to convert callback to coroutines
    @SuppressLint("MissingPermission")
    suspend fun getLastKnowLocation(): Location {
        return suspendCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnCompleteListener { locationTask ->
                if (locationTask.isSuccessful) {
                    locationTask.result?.let { location ->
                        continuation.resume(location)
                    } ?: run {
                        continuation.resumeWithException(NullPointerException("Location is null"))
                    }
                } else {
                    continuation.resumeWithException(locationTask.exception!!)
                }
            }
        }
    }
}