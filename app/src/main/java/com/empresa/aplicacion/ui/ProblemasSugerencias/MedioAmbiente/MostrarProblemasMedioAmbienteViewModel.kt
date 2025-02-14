package com.empresa.aplicacion.ui.ProblemasSugerencias.MedioAmbiente

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
class MostrarProblemasMedioAmbienteViewModel @Inject constructor(
    private val getProblemasFlowConvertirUseCase: GetProblemasFlowConvertirUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<MedioAmbienteState>(MedioAmbienteState.Loading)
    val state: StateFlow<MedioAmbienteState> = _state

    init {
        viewModelScope.launch {
            _state.value = MedioAmbienteState.Loading
            try {
            getProblemasFlowConvertirUseCase("Medio Ambiente")
                .collect { problemasRegistrados ->
                    _state.value = MedioAmbienteState.Success(problemasRegistrados)
                }
            } catch (e: Throwable) {
                _state.value = MedioAmbienteState.Error("Fallo al obtener la informacion")
            }
        }
    }


}

sealed interface MedioAmbienteState {
    data class Success(val problemas: List<Problema>) : MedioAmbienteState
    data class Error(val error: String) : MedioAmbienteState
    object Loading : MedioAmbienteState
}