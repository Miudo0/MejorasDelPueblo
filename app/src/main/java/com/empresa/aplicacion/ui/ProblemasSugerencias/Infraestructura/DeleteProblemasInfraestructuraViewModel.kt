package com.empresa.aplicacion.ui.ProblemasSugerencias.Infraestructura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.domain.DeleteProblemasInfraestructuraUseCase
import com.empresa.aplicacion.domain.GetMostrarProblemasFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteProblemasInfraestructuraViewModel @Inject constructor(
    private val deleteProblemasInfraestructuraUseCase: DeleteProblemasInfraestructuraUseCase,
//    private val getProblemas: MostrarProblemasUseCase,
private val mostrarProblemasFlowUseCase: GetMostrarProblemasFlowUseCase
    ) : ViewModel() {

    private val _state = MutableStateFlow<DeleteProblemasInfraestructuraState>(DeleteProblemasInfraestructuraState.Loading)
    val state: StateFlow<DeleteProblemasInfraestructuraState> = _state


  fun deleteProblema(problema: Problemas) {
        viewModelScope.launch {
            _state.value = DeleteProblemasInfraestructuraState.Loading
            try {
                deleteProblemasInfraestructuraUseCase(problema)

//             mostrarProblemasFlowUseCase(tipo = "Infraestructura")
//                _state.value = DeleteProblemasInfraestructuraState.Success(nuevaLista)


            } catch (e: Throwable) {
                _state.value = DeleteProblemasInfraestructuraState.Error("Error al eliminar")
            }
        }
    }

    sealed interface DeleteProblemasInfraestructuraState {
        data class Success(val problemas: List<Problemas>) : DeleteProblemasInfraestructuraState
        data class Error(val error: String) : DeleteProblemasInfraestructuraState
        object Loading : DeleteProblemasInfraestructuraState
    }
}