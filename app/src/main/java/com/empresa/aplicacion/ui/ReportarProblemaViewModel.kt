package com.empresa.aplicacion.ui

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
            nuevoProblemaUseCase(titulo, descripcion, tipo)
        }

    }

    sealed interface NewProblemState {
        data object Loading : NewProblemState
        data class Success(val username: String) : NewProblemState
        data class Error(val mensajeError: String) : NewProblemState

    }
}