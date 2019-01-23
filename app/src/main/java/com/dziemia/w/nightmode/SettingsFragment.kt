package com.dziemia.w.nightmode

import android.os.Bundle
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_general)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setBackgroundResource(R.color.windowBackground)

        findPreference("pref_dark_theme").onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, _ ->
                    requireActivity().sendLocalBroadcast(PREF_NIGHT_MODE_CHANGE)
                    true
                }
    }

    companion object {
        const val PREF_NIGHT_MODE_CHANGE = "PREF_NIGHT_MODE_CHANGE"
    }
}
