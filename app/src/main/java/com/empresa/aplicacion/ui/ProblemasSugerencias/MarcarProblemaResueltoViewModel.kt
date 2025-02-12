package com.empresa.aplicacion.ui.ProblemasSugerencias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.domain.DeleteProblemasUseCase
import com.empresa.aplicacion.domain.UpdateProblemaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarcarProblemaResueltoViewModel @Inject constructor(
    private val updateProblemaUseCase: UpdateProblemaUseCase,
    private val deleteProblemasUseCase: DeleteProblemasUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ProblemaState>(ProblemaState.Loading)
    val state: StateFlow<ProblemaState> = _state

    fun marcarComoResuelto(problema: Problemas) {
        viewModelScope.launch {
            // Cambiar estado 'resuelto' a true
            val updatedProblema = problema.copy(resuelto = true)
            updateProblemaUseCase(updatedProblema)
            _state.value = ProblemaState.Success(updatedProblema)
        }
    }


    sealed class ProblemaState {
        data class Success(val problema: Problemas) : ProblemaState()
        object Loading : ProblemaState()
        data class Error(val message: String) : ProblemaState()
    }
}



