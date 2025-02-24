package com.empresa.aplicacion.ui.problemasSugerencias.reportar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.NuevoProblemaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportarProblemaViewModel @Inject constructor(
    private val nuevoProblemaUseCase: NuevoProblemaUseCase,
    comprobarUsuario: GetUserSharedPreferencesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<NewProblemState>(NewProblemState.Loading)
    val state: MutableStateFlow<NewProblemState> = _state

    private val usuarioActual = comprobarUsuario.getUserFromSharedPreferences() ?: "Invitado"
    init {
        Log.d("ReportarProblemaViewModel", "Usuario actual: $usuarioActual")
    }

    fun nuevoProblema(titulo: String, descripcion: String, tipo: String) {
        viewModelScope.launch {
            try {
                Log.d("ReportarProblemaViewModel", "Intentando reportar problema...")
                nuevoProblemaUseCase(titulo, descripcion, tipo, usuarioActual)
                _state.value = NewProblemState.Success("Reporte realizado")
                Log.d("ReportarProblemaViewModel", "Reporte exitoso")
                Log.d("ReportarProblemaViewModel", "Reporte realizado")

            }catch (
                e: Exception
            ){
                _state.value = NewProblemState.Error("Error al registrarse")
                Log.e("ReportarProblemaViewModel", "Error al reportar: ${e.message}", e)
            }


        }

    }

    sealed interface NewProblemState {
        data object Loading : NewProblemState
        data class Success(val reportado: String) : NewProblemState
        data class Error(val mensajeError: String) : NewProblemState

    }
}