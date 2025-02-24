package com.empresa.aplicacion.ui.problemasSugerencias.trafico

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
class DeleteProblemasTraficoViewModel @Inject constructor(
    private val deleteProblemasTrafico: DeleteProblemasUseCase
) : ViewModel() {
    private val _state =
        MutableStateFlow<DeleteProblemasTraficoState>(DeleteProblemasTraficoState.Loading)
    val state: StateFlow<DeleteProblemasTraficoState> = _state

    fun deleteProblemasTrafico(problema: Problema) {
        viewModelScope.launch {
            _state.value = DeleteProblemasTraficoState.Loading
            try {
                val problemaEntity = problema.toEntity()
                deleteProblemasTrafico(problemaEntity)

            } catch (e: Throwable) {
                _state.value = DeleteProblemasTraficoState.Error("Error al eliminar")
            }
        }
    }


    sealed interface DeleteProblemasTraficoState {
        data class Success(val problemas: List<ProblemasEntity>) : DeleteProblemasTraficoState
        data class Error(val error: String) : DeleteProblemasTraficoState
        object Loading : DeleteProblemasTraficoState


    }
}