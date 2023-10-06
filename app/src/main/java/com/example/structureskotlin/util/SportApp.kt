package com.example.structureskotlin.util

import android.app.Application
import android.util.Base64
import com.example.structureskotlin.R
import com.google.android.libraries.places.api.Places
import com.intuit.sdp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SportApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun decodeGoogleMapKey(base64EncodedKey: String): ByteArray {
        var decodedKey = byteArrayOf()
        try {
            decodedKey = Base64.decode(base64EncodedKey, Base64.DEFAULT)
        } catch (e: Exception) {
          //  printErrorLog("Error while decoding: ${e.printStackTrace()}")
        } catch (e: java.lang.Exception) {
           // printErrorLog("Error while decoding: ${e.printStackTrace()}")
        }
        return decodedKey
    }
}