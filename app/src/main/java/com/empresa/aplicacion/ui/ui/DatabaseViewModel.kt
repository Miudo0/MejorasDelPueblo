package com.empresa.aplicacion.ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.AppDatabase
import com.empresa.aplicacion.data.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {
    private var _state = MutableStateFlow<String?>(null)
    val state = _state


        fun getUsuario(usuario: String, pass: String) {
            viewModelScope.launch {

                val userDao = appDatabase.usuariosDao()//hilt aqui ya me injecta la bdd
                val usuarioDb = userDao.getUserName(usuario, pass)
                _state.value = usuarioDb ?: "Usuario no encontrado"

            }
        }

    fun insertarUsuario(usuario: String, pass: String, email: String){
        viewModelScope.launch {
            val userDao = appDatabase.usuariosDao()
            val nuevoUsuario = Usuario(username =usuario, pass = pass , email = email)
            userDao.insertAll(nuevoUsuario)
            _state.value = "correcto"
        }


    }


}