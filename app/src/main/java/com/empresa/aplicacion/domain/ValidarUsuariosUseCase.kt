package com.empresa.aplicacion.domain

import android.content.SharedPreferences
import com.empresa.aplicacion.data.repository.UsuariosRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidarUsuariosUseCase @Inject constructor(
    private val repository: UsuariosRepository,
    private val sharedPreferences: SharedPreferences

) {
    suspend operator fun invoke(username: String, pass: String): String? {
        return withContext(IO) {
            val usuario = repository.getUsuarioRegistrado(username, pass)
            usuario?.let { saveUserToSharedPreferences(it) }
            usuario
        }
    }


    fun getUserFromSharedPreferences(): String? {
        return sharedPreferences.getString("logeado", null)
    }

    fun saveUserToSharedPreferences(username: String) {
        sharedPreferences.edit()
            .putString("logeado", username)
            .apply()
    }

}
