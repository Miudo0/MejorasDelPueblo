package com.empresa.aplicacion.data.repository

import android.content.SharedPreferences
import com.empresa.aplicacion.data.room.UsuariosDatabase.Usuario
import com.empresa.aplicacion.data.room.UsuariosDatabase.UsuariosDatabase
import javax.inject.Inject

class UsuariosRepository @Inject constructor(
    private val appDatabase: UsuariosDatabase,
    private val sharedPreferences: SharedPreferences
){
    suspend fun getUsuarioRegistrado (username: String, pass: String): String?{
        val userDao = appDatabase.usuariosDao()
        return userDao.getUserName( username, pass)
    }

    suspend fun newUser (username: String, pass: String, email: String){
        val userDao = appDatabase.usuariosDao()
        val nuevoUsuario = Usuario(username = username, pass = pass, email = email)
        userDao.insertAll(nuevoUsuario)
    }
}
