package com.empresa.aplicacion.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.AppDatabase
import com.empresa.aplicacion.data.room.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {
    private var _state = MutableStateFlow<databaseState>(databaseState.Loading)
    val state : MutableStateFlow<databaseState> = _state

    private var _navegacionState = MutableStateFlow<NavigationState?>(null)
    val navegacionState: MutableStateFlow<NavigationState?> = _navegacionState

    private val _errorMessageState = MutableStateFlow<errorState>(errorState.Success(""))
    val errorMessageState: StateFlow<errorState> = _errorMessageState


    fun getUsuario(usuario: String, pass: String) {
        viewModelScope.launch {

            val userDao = appDatabase.usuariosDao()
            val usuarioDb = userDao.getUserName(usuario, pass)

            if (usuarioDb != null) {
             _state.value = databaseState.Success(usuarioDb)
                _navegacionState.value = NavigationState.NavigateToHome

            } else {
                _state.value = databaseState.Error
                Log.e("DatabaseViewModel", "Usuario o contraseña incorrectos")
                _errorMessageState.value = errorState.Success("Usuario o contraseña incorrectos")
            }
        }
    }


    fun insertarUsuario(usuario: String, pass: String, email: String) {
        viewModelScope.launch {
            val userDao = appDatabase.usuariosDao()
            val nuevoUsuario = Usuario(username = usuario, pass = pass, email = email)
            userDao.insertAll(nuevoUsuario)
            _state.value = nuevoUsuario.username?.let { databaseState.Success(it) }!!






        }
    }

    //state para el login
    sealed interface databaseState {
        data object Loading : databaseState
        data class Success (val username: String) : databaseState
        data object Error : databaseState
    }

sealed interface errorState {
        data class Success (val mensajeError: String) : errorState
}



    sealed interface NavigationState {
      data  object NavigateToHome : NavigationState
    }

}