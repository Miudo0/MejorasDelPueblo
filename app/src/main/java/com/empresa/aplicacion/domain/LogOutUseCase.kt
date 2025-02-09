package com.empresa.aplicacion.domain

import android.content.SharedPreferences
import com.empresa.aplicacion.data.repository.UsuariosRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: UsuariosRepository,
    private val sharedPreferences: SharedPreferences
) {

    fun logout() {
        sharedPreferences.edit()
            .remove("logeado")
            .apply()
    }


}