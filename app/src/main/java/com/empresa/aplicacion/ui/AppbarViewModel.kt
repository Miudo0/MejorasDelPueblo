package com.empresa.aplicacion.ui

import androidx.lifecycle.ViewModel
import com.empresa.aplicacion.domain.ValidarUsuariosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AppbarViewModel @Inject constructor(
    private val validar: ValidarUsuariosUseCase,

) : ViewModel() {
    private var _username = MutableStateFlow("Invitado")
    val username: MutableStateFlow<String> = _username

    private var _navegacionState = MutableStateFlow<NavigationStateCerrarSesion?>(null)
    val navegacionState: MutableStateFlow<NavigationStateCerrarSesion?> = _navegacionState

    init {
        val savedUser = validar.getUserFromSharedPreferences()
        savedUser?.let {
            _username.value = it
        }
    }


    fun logout() {
        validar.logout()
        _navegacionState.value = NavigationStateCerrarSesion.NavigateToLogin
    }

    sealed interface NavigationStateCerrarSesion {
        object NavigateToLogin : NavigationStateCerrarSesion

    }
}