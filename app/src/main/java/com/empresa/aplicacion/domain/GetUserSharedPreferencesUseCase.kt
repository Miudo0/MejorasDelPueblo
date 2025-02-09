package com.empresa.aplicacion.domain

import android.content.SharedPreferences
import javax.inject.Inject

class GetUserSharedPreferencesUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,

) {
    fun getUserFromSharedPreferences(): String? {
        return sharedPreferences.getString("logeado", null)
    }
}