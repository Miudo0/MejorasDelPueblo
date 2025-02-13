package com.empresa.aplicacion.ui.ProblemasSugerencias.Infraestructura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.GetMostrarProblemasFlowUseCase
import com.empresa.aplicacion.domain.GetProblemasFlowConvertirUseCase
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.Problema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InfraestructuraViewModel @Inject constructor(
    private val getMostrarProblemasFlowUseCase : GetMostrarProblemasFlowUseCase,
    private val GetProblemasFlowConvertirUseCase: GetProblemasFlowConvertirUseCase,
    getUser: GetUserSharedPreferencesUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<InfraestructuraState>(InfraestructuraState.Loading)
    val state: StateFlow<InfraestructuraState> = _state


    init {
        viewModelScope.launch {
            _state.value = InfraestructuraState.Loading
            try {
               GetProblemasFlowConvertirUseCase("Infraestructura")
                   .collect { problemasRegistrados ->
                        _state.value = InfraestructuraState.Success(problemasRegistrados)
                    }

            } catch (e: Throwable) {
                _state.value = InfraestructuraState.Error("No hay nada")
            }
        }
    }


    sealed interface InfraestructuraState {
        data class Success(val problemas: List<Problema>) : InfraestructuraState
        data class Error(val error: String) : InfraestructuraState
        object Loading : InfraestructuraState

    }
}