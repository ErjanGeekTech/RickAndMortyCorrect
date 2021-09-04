package com.example.rickandmortycorrect.data.preference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.rickandmortycorrect.constants.Constants
import javax.inject.Inject

class LanguageSharedPreferences @Inject constructor(private val preferences: SharedPreferences) {

    fun getLanguage() = preferences.getBoolean(Constants.RICK_AND_MORTY_LANGUAGE, false)

    fun setLanguage(value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(Constants.RICK_AND_MORTY_LANGUAGE, value)
        editor.apply()
    }

}