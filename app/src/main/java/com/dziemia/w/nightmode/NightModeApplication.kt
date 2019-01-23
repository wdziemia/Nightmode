package com.dziemia.w.nightmode

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate


class NightModeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initNightMode()
    }

    private fun initNightMode() {
        val darkModeValue = SettingsHelper(this).getDarkModeValue()
        AppCompatDelegate.setDefaultNightMode(darkModeValue)
    }

}
