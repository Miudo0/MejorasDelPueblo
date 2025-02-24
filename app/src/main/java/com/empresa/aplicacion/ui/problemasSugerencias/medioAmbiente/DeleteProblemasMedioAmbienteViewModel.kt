package com.empresa.aplicacion.ui.problemasSugerencias.medioAmbiente

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
class DeleteProblemasMedioAmbienteViewModel @Inject constructor(
    private val deleteProblemasMedioAmbienteUseCase: DeleteProblemasUseCase,

    ) : ViewModel() {

    private val _state =
        MutableStateFlow<DeleteProblemasMedioambienteState>(DeleteProblemasMedioambienteState.Loading)
    val state: StateFlow<DeleteProblemasMedioambienteState> = _state


    fun deleteProblemasMedioAmbiente(problema: Problema) {
        viewModelScope.launch {
            _state.value = DeleteProblemasMedioambienteState.Loading
            try {
                val problemaEntity = problema.toEntity()
                deleteProblemasMedioAmbienteUseCase(problemaEntity)

            } catch (e: Throwable) {
                _state.value = DeleteProblemasMedioambienteState.Error("Error al eliminar")
            }
        }
    }

    sealed interface DeleteProblemasMedioambienteState {
        data class Success(val problemas: List<ProblemasEntity>) : DeleteProblemasMedioambienteState
        data class Error(val error: String) : DeleteProblemasMedioambienteState
        object Loading : DeleteProblemasMedioambienteState
    }



}