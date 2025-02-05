package com.empresa.aplicacion.ui.ProblemasSugerencias.Reportar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.NuevoProblemaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportarProblemaViewModel @Inject constructor(
    private val nuevoProblemaUseCase: NuevoProblemaUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<NewProblemState>(NewProblemState.Loading)
    val state: MutableStateFlow<NewProblemState> = _state

    fun nuevoProblema(titulo: String, descripcion: String, tipo: String) {
        viewModelScope.launch {
            try {
                nuevoProblemaUseCase(titulo, descripcion, tipo)
                _state.value = NewProblemState.Success("Reporte realizado")

            }catch (
                e: Exception
            ){
                _state.value = NewProblemState.Error("Error al registrarse")
            }


        }

    }

    sealed interface NewProblemState {
        data object Loading : NewProblemState
        data class Success(val reportado: String) : NewProblemState
        data class Error(val mensajeError: String) : NewProblemState

    }
}