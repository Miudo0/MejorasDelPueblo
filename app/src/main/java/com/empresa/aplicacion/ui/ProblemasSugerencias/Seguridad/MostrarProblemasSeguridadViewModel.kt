package com.empresa.aplicacion.ui.ProblemasSugerencias.Seguridad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.domain.GetMostrarProblemasFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MostrarProblemasSeguridadViewModel @Inject constructor(
    private val getMostrarProblemasFlowUseCase: GetMostrarProblemasFlowUseCase,
) : ViewModel()
{
    private val _state = MutableStateFlow<SeguridadState>(SeguridadState.Loading)
    val state = _state

    init {
        viewModelScope.launch {
           _state.value = SeguridadState.Loading
            try {
             getMostrarProblemasFlowUseCase("Seguridad")
                 .collect { problemasRegistrados ->
                     _state.value = SeguridadState.Success(problemasRegistrados)
                 }
            } catch (e: Throwable) {
                _state.value = SeguridadState.Error("Fallo al obtener la informacion")
            }

        }
    }
}



sealed interface SeguridadState{
    data class Success(val problemas: List<Problemas>) : SeguridadState
    data class Error(val error: String) : SeguridadState
    object Loading : SeguridadState

}