package com.empresa.aplicacion.ui.RegistroTareasPendientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.MostrarProblemasPorUsuarioUseCase
import com.empresa.aplicacion.domain.Problema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val mostrarProblemasPorUsuarioUseCase: MostrarProblemasPorUsuarioUseCase,
    private val usuarioActivo: GetUserSharedPreferencesUseCase
) : ViewModel() {
   val usuario = usuarioActivo.getUserFromSharedPreferences()

    private val _state = MutableStateFlow<RegistroState>(RegistroState.Loading)
    val state: StateFlow<RegistroState> = _state


    init {
        viewModelScope.launch {
            _state.value = RegistroState.Loading
            try {
           mostrarProblemasPorUsuarioUseCase(usuario.toString())
                    .collect { problemasRegistrados ->
                        _state.value =RegistroState.Success(problemasRegistrados)
                    }

            } catch (e: Throwable) {
                _state.value = RegistroState.Error("No hay nada")
            }
        }
    }





}

sealed interface RegistroState {
    data class Success(val problemas: List<Problema>) : RegistroState
    data class Error(val error: String) : RegistroState
    object Loading : RegistroState
}