package com.empresa.aplicacion.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.NewUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertarUsuarioViewModel @Inject constructor(
    private val NewUser: NewUserUseCase
) : ViewModel() {


    private var _state = MutableStateFlow<NewUserState>(NewUserState.Loading)
    val state: MutableStateFlow<NewUserState> = _state

    private var _navegacionState = MutableStateFlow<NavigationState?>(null)
    val navegacionState: MutableStateFlow<NavigationState?> = _navegacionState


    fun insertarUsuario(usuario: String, pass: String, email: String) {
        viewModelScope.launch {
            try {
                val nuevoUsuario = NewUser(usuario, pass, email)
                _state.value = NewUserState.Success(nuevoUsuario.toString())
                _navegacionState.value = NavigationState.NavigateToLogin
            } catch (e: Exception) {
                Log.e("DatabaseViewModel", "Error al registrarse", e)
                _state.value = NewUserState.Error("Error al registrarse")

            }
        }
    }

    //state para el login
    sealed interface NewUserState {
        data object Loading : NewUserState
        data class Success(val username: String) : NewUserState
        data class Error(val mensajeError: String) : NewUserState
    }

    sealed interface NavigationState {
        data object NavigateToLogin : NavigationState
    }


}