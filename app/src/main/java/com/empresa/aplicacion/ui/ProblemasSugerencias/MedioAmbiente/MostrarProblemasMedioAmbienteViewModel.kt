package com.empresa.aplicacion.ui.ProblemasSugerencias.MedioAmbiente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.domain.MostrarProblemasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostrarProblemasMedioAmbienteViewModel @Inject constructor(
    private val getproblemasUseCase: MostrarProblemasUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<MedioAmbienteState>(MedioAmbienteState.Loading)
   val state: StateFlow<MedioAmbienteState> = _state

    init {
        viewModelScope.launch {
            _state.value = MedioAmbienteState.Loading
            try {
                _state.value = MedioAmbienteState.Success(getproblemasUseCase("Medio Ambiente"))
            } catch (e: Throwable) {
                _state.value = MedioAmbienteState.Error("Fallo al obtener la informacion")
            }
        }
    }


}

sealed interface MedioAmbienteState {
    data class Success(val problemas: List<Problemas>) : MedioAmbienteState
    data class Error(val error: String) : MedioAmbienteState
    object Loading : MedioAmbienteState
}