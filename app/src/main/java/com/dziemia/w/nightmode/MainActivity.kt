package com.dziemia.w.nightmode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.dziemia.w.nightmode.SettingsFragment.Companion.PREF_NIGHT_MODE_CHANGE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var batteryReceiver : BroadcastReceiver
    private lateinit var nightModeReceiver : BroadcastReceiver
    private val settingsHelper : SettingsHelper by lazy { SettingsHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        batteryReceiver = registerReceiverAction("android.os.action.POWER_SAVE_MODE_CHANGED") { _: Context, _: Intent ->
            applyNightMode()
        }

        nightModeReceiver = registerLocalReceiverAction(PREF_NIGHT_MODE_CHANGE) { _: Context, _: Intent ->
            applyNightMode()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    fun onSettingsClicked(view: View){
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, SettingsFragment())
            .addToBackStack("Settings")
            .commit()
    }

    private fun applyNightMode() {
        val darkModeValue = settingsHelper.getDarkModeValue()
        AppCompatDelegate.setDefaultNightMode(darkModeValue)
        delegate.setLocalNightMode(darkModeValue)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
        unregisterLocalReceiver(nightModeReceiver)
    }
}
