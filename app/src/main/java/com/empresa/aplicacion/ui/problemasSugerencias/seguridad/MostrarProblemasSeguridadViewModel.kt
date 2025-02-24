package com.empresa.aplicacion.ui.problemasSugerencias.seguridad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.GetProblemasFlowConvertirUseCase
import com.empresa.aplicacion.domain.Problema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MostrarProblemasSeguridadViewModel @Inject constructor(
    private val getProblemasFlowConvertirUseCase: GetProblemasFlowConvertirUseCase,
) : ViewModel()
{
    private val _state = MutableStateFlow<SeguridadState>(SeguridadState.Loading)
    val state: StateFlow<SeguridadState> = _state

    init {
        viewModelScope.launch {
           _state.value = SeguridadState.Loading
            try {
             getProblemasFlowConvertirUseCase("Seguridad")
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
    data class Success(val problemas: List<Problema>) : SeguridadState
    data class Error(val error: String) : SeguridadState
    object Loading : SeguridadState

}