package com.empresa.aplicacion.ui.problemasSugerencias.infraestructura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.problemasDatabase.ProblemasEntity
import com.empresa.aplicacion.data.room.problemasDatabase.toEntity
import com.empresa.aplicacion.domain.DeleteProblemasUseCase
import com.empresa.aplicacion.domain.Problema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteProblemasInfraestructuraViewModel @Inject constructor(
    private val deleteProblemasInfraestructuraUseCase: DeleteProblemasUseCase,

    ) : ViewModel() {

    private val _state =
        MutableStateFlow<DeleteProblemasInfraestructuraState>(DeleteProblemasInfraestructuraState.Loading)
    val state: StateFlow<DeleteProblemasInfraestructuraState> = _state


    fun deleteProblema(problema: Problema) {
        viewModelScope.launch {
            _state.value = DeleteProblemasInfraestructuraState.Loading
            try {
                val problemaEntity = problema.toEntity()
                deleteProblemasInfraestructuraUseCase(problemaEntity)

            } catch (e: Throwable) {
                _state.value = DeleteProblemasInfraestructuraState.Error("Error al eliminar")
            }
        }
    }

    sealed interface DeleteProblemasInfraestructuraState {
        data class Success(val problemas: List<ProblemasEntity>) : DeleteProblemasInfraestructuraState
        data class Error(val error: String) : DeleteProblemasInfraestructuraState
        object Loading : DeleteProblemasInfraestructuraState
    }
}