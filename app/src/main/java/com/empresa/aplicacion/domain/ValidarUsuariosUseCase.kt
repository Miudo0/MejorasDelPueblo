package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.UsuariosRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidarUsuariosUseCase @Inject constructor(
    private val repository: UsuariosRepository,
    private val saveUserToSharedPreferences: SaveUserSharedPreferencesUseCase
) {
    suspend operator fun invoke(username: String, pass: String): String? {
        return withContext(IO) {
            val usuario = repository.getUsuarioRegistrado(username, pass)
            usuario?.let { saveUserToSharedPreferences.saveUserToSharedPreferences(it) }
            usuario
        }
    }


}
