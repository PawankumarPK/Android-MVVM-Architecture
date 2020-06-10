package com.handypawan.mvvmexampleproject.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by pawan on 10,June,2020
 */

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(context: Context){

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedIt(savedAt: String){
        preferences.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()

    }

    fun getLastSavedAt(): String?{
        return preferences.getString(KEY_SAVED_AT,null)

    }

}