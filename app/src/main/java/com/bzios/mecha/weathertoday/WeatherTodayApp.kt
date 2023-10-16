package com.bzios.mecha.weathertoday

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import timber.log.Timber

class WeatherTodayApp : Application() {

    companion object {
        lateinit var instanceAppContext: WeatherTodayApp
        lateinit var instanceAppPreference: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        instanceAppContext = this

        initialTimber()
        initialSharePreference()
    }

    private fun initialTimber() = Timber.plant(Timber.DebugTree())
    private fun initialSharePreference() {
        val masterKey = MasterKey.Builder(instanceAppContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        instanceAppPreference = EncryptedSharedPreferences.create(
            instanceAppContext,
            "ap_family_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }
}