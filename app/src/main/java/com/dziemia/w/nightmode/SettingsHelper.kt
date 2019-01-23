package com.dziemia.w.nightmode

import android.content.Context
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate

class SettingsHelper(val context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val powerManager = context.getPowerManager()

    fun getDarkModeValue() : Int {
        val value = preferences.getString("pref_dark_theme", "0")?.toInt()
        return when {
            value == 3 -> AppCompatDelegate.MODE_NIGHT_YES // Always
            value == 2 && powerManager.isPowerSaveMode -> AppCompatDelegate.MODE_NIGHT_YES // Auto && Battery Saver
            value == 2 -> AppCompatDelegate.MODE_NIGHT_AUTO // Always
            value == 1 && powerManager.isPowerSaveMode -> AppCompatDelegate.MODE_NIGHT_YES // Is Only battery Saver
            else -> AppCompatDelegate.MODE_NIGHT_NO // Never

        }
    }
}