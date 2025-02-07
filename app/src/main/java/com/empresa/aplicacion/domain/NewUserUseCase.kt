package com.empresa.aplicacion.domain

import android.content.SharedPreferences
import com.empresa.aplicacion.data.repository.UsuariosRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewUserUseCase @Inject constructor(
    private val repository: UsuariosRepository,
    private val sharedPreferences: SharedPreferences

) {
    suspend operator fun invoke(username: String, pass: String, email: String) {
        return withContext(IO) {

            repository.newUser(username, pass, email)

        }


    }
}
