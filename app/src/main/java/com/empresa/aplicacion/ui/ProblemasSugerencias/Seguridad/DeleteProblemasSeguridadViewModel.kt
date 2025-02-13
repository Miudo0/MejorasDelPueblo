package com.empresa.aplicacion.ui.ProblemasSugerencias.Seguridad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.ProblemasDatabase.ProblemasEntity
import com.empresa.aplicacion.domain.DeleteProblemasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeleteProblemasSeguridadViewModel @Inject constructor(
    private val deleteProblemasUseCase: DeleteProblemasUseCase

): ViewModel() {

    private val _state =
        MutableStateFlow<DeleteProblemasSeguridadState>(DeleteProblemasSeguridadState.Loading)
    val state: StateFlow<DeleteProblemasSeguridadState> = _state


    fun deleteProblemaSeguridad(problema: ProblemasEntity) {
        viewModelScope.launch {
            _state.value = DeleteProblemasSeguridadState.Loading
            try {
                deleteProblemasUseCase(problema)
            } catch (e: Throwable) {
                _state.value = DeleteProblemasSeguridadState.Error("Error al eliminar")
            }
        }
    }

    sealed interface DeleteProblemasSeguridadState {
        data class Success(val problemas: List<ProblemasEntity>) : DeleteProblemasSeguridadState
        data class Error(val error: String) : DeleteProblemasSeguridadState
        object Loading : DeleteProblemasSeguridadState
    }


}