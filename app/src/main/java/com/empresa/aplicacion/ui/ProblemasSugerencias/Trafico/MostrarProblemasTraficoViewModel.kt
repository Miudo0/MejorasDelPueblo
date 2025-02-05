package com.empresa.aplicacion.ui.ProblemasSugerencias.Trafico

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
class MostrarProblemasTraficoViewModel @Inject constructor(
    private val getProblemasUseCase: MostrarProblemasUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<TraficoState>(TraficoState.Loading)
    val state: StateFlow<TraficoState> = _state

    init {
        viewModelScope.launch {
            _state.value = TraficoState.Loading
            try {
                _state.value = TraficoState.Success(getProblemasUseCase("Trafico"))
            } catch (e: Throwable) {
                _state.value = TraficoState.Error("Fallo al obtener la informacion")
            }
        }
    }


}

sealed interface TraficoState {
    data class Success(val problemas: List<Problemas>) : TraficoState
    data class Error(val error: String) : TraficoState
    object Loading : TraficoState

}