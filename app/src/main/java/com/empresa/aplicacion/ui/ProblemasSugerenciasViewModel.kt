package com.empresa.aplicacion.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.MostrarProblemasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProblemasSugerenciasViewModel  @Inject constructor(
    private val getProblemasTipo: MostrarProblemasUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<GetProblemasState>(GetProblemasState.Loading)
    var state: MutableStateFlow<GetProblemasState> = _state

    fun getProblemas(tipo: String){
    viewModelScope.launch {
            getProblemasTipo(tipo = tipo)
        }
    }

}

sealed interface GetProblemasState {
    data object Loading :GetProblemasState
    data class Success(val username: String) : GetProblemasState
    data class Error(val mensajeError: String) : GetProblemasState
}
