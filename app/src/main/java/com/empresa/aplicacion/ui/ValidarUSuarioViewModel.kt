package com.empresa.aplicacion.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.ValidarUsuariosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ValidarUSuarioViewModel @Inject constructor(
    // private val appDatabase: AppDatabase,
    private val getUsuarioRegistrado: ValidarUsuariosUseCase,


    ) : ViewModel() {
    private var _state = MutableStateFlow<databaseState>(databaseState.Loading)
    val state: MutableStateFlow<databaseState> = _state

    private var _navegacionState = MutableStateFlow<NavigationState?>(null)
    val navegacionState: MutableStateFlow<NavigationState?> = _navegacionState

    private val _errorMessageState = MutableStateFlow<errorState>(errorState.Success(""))
    val errorMessageState: StateFlow<errorState> = _errorMessageState

   //Para el nombre de usuario
    private var _username = MutableStateFlow("Invitado")
    val username: StateFlow<String> = _username



    fun getUsuario(usuario: String, pass: String) {
        viewModelScope.launch {

            val usuarioDb = getUsuarioRegistrado(usuario, pass)

            if (usuarioDb != null) {
                _username.value = usuarioDb
                Log.d("DatabaseViewModel", "Username actualizado: $usuarioDb")
                _state.value = databaseState.Success(usuarioDb)
                _navegacionState.value = NavigationState.NavigateToHome(usuarioDb)

            } else {
                _state.value = databaseState.Error
                Log.e("DatabaseViewModel", "Usuario o contraseña incorrectos")
                _errorMessageState.value = errorState.Success("Usuario o contraseña incorrectos")
            }
        }
    }




    //state para el login
    sealed interface databaseState {
        data object Loading : databaseState
        data class Success(val username: String) : databaseState
        data object Error : databaseState
    }

    sealed interface errorState {
        data class Success(val mensajeError: String) : errorState
    }


    sealed interface NavigationState {
        data class NavigateToHome(val username: String) : NavigationState
    }

}