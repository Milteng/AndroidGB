package com.example.m11_timer_data_storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private const val PREFERENCE = "preference"
private const val STRING_KEY = "variable"


data class Repository (val context: Context) {

    var localString: String? = null
    val preference = context.getSharedPreferences(PREFERENCE, MODE_PRIVATE)
    val editor :SharedPreferences.Editor = preference.edit()

    fun getText(): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference() != null -> getDataFromSharedPreference()!!
            else -> ""
        }
    }

    fun getDataFromSharedPreference(): String? {
        val sharedPrefString: String? = preference.getString(STRING_KEY, null)
        return sharedPrefString
    }

    fun getDataFromLocalVariable(): String? {
        return localString
    }

    fun saveText(text: String) {
        localString = text
        editor.putString(STRING_KEY, text).apply()
    }

    fun clearText() {
        localString = null
        editor.clear().apply()
    }
}