package com.empresa.aplicacion.data.repository

import com.empresa.aplicacion.data.room.AppDatabase
import com.empresa.aplicacion.data.room.Usuario
import javax.inject.Inject

class UsuariosRepository @Inject constructor(
    private val appDatabase: AppDatabase
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