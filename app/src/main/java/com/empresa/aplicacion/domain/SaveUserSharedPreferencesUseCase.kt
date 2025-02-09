package com.empresa.aplicacion.domain

import android.content.SharedPreferences
import javax.inject.Inject

class SaveUserSharedPreferencesUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences

) {

    fun saveUserToSharedPreferences(username: String) {
        sharedPreferences.edit()
            .putString("logeado", username)
            .apply()
    }


}