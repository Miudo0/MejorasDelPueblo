package com.empresa.aplicacion.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppbarViewModel @Inject constructor(

    private val cerrarSesion: LogOutUseCase,
    private val comprobarSesion: GetUserSharedPreferencesUseCase

) : ViewModel() {
    private var _username = MutableStateFlow("Buenos días")
    val username: MutableStateFlow<String> = _username

    private var _navegacionState = MutableSharedFlow<NavigationStateCerrarSesion>()
    val navegacionState  = _navegacionState


    init {
        Log.d("AppbarViewModel", "Inicializando AppbarViewModel")
        val savedUser = comprobarSesion.getUserFromSharedPreferences()
        Log.d("AppbarViewModel", "Usuario guardado: $savedUser")
        savedUser?.let {
            _username.value = it
        }
    }


    fun logout() {
        Log.d("Logout", "Iniciando proceso de cierre de sesión.")
        cerrarSesion.logout()
        _username.value = "Invitado"
        Log.d("Logout", "Usuario cambiado a 'Invitado'.")

        viewModelScope.launch(IO) {
            _navegacionState.emit(NavigationStateCerrarSesion.NavigateToLogin)
            Log.d("Logout", "Navegación emitida: NavigateToLogin")
        }

    }

    sealed interface NavigationStateCerrarSesion {
        data object NavigateToLogin : NavigationStateCerrarSesion

    }

}