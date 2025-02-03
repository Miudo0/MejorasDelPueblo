package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.UsuariosRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsuariosUseCase @Inject constructor(
    private val repository: UsuariosRepository

) {
    suspend operator fun invoke(username: String, pass: String): String? {
        return withContext(IO) {
            repository.getUsuarioRegistrado(username, pass)
        }
    }

    suspend operator fun invoke(username: String, pass: String, email: String){
        return withContext(IO) {
            repository.NewUser(username, pass, email)
        }
    }
}