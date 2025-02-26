package com.empresa.aplicacion.ui.problemasSugerencias.reportar

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.NuevoProblemaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

@HiltViewModel
class ReportarProblemaViewModel @Inject constructor(
    private val nuevoProblemaUseCase: NuevoProblemaUseCase,
    comprobarUsuario: GetUserSharedPreferencesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<NewProblemState>(NewProblemState.Loading)
    val state: MutableStateFlow<NewProblemState> = _state

    private val usuarioActual = comprobarUsuario.getUserFromSharedPreferences() ?: "Invitado"

    // Estado para almacenar la imagen capturada
    private var _imagenUri = MutableStateFlow<Uri?>(null)
    val imagenUri: MutableStateFlow<Uri?> = _imagenUri

    private var _ubicacionSeleccionada = MutableStateFlow<GeoPoint?>(null)
    val ubicacionSeleccionada: MutableStateFlow<GeoPoint?> = _ubicacionSeleccionada


    //prueba para incluir imagenes
    fun setImagen(uri: Uri) {
        _imagenUri.value = uri
        Log.d("ReportarProblemaViewModel", "Imagen guardada en ViewModel: $uri")
    }

    fun setUbicacionSeleccionada(ubicacion: GeoPoint) {
        _ubicacionSeleccionada.value = ubicacion
        Log.d("ReportarProblemaViewModel", "Ubicación seleccionada guardada en ViewModel: Lat: ${ubicacion.latitude}, Lon: ${ubicacion.longitude}")
    }


    fun nuevoProblema(titulo: String, descripcion: String, tipo: String) {
        viewModelScope.launch {
            try {
                val imagen = _imagenUri.value
                val ubicacion = _ubicacionSeleccionada.value
                Log.d("ReportarProblemaViewModel", "Intentando reportar problema con imagen: $imagen")


                Log.d("ReportarProblemaViewModel", "Intentando reportar problema...")
                nuevoProblemaUseCase(titulo, descripcion, tipo, usuarioActual,imagen,ubicacion)
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