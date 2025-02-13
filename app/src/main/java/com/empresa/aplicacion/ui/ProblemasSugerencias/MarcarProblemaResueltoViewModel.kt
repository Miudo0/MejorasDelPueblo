package com.empresa.aplicacion.ui.ProblemasSugerencias

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.room.ProblemasDatabase.toEntity
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.Problema
import com.empresa.aplicacion.domain.UpdateProblemaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarcarProblemaResueltoViewModel @Inject constructor(
    private val updateProblemaUseCase: UpdateProblemaUseCase,
    private val Problema: Problema,
      getUser: GetUserSharedPreferencesUseCase
) : ViewModel() {

    private val usuarioActual = getUser.getUserFromSharedPreferences() ?: "Invitado"

//    private val _state = MutableStateFlow<ProblemaState>(ProblemaState.Loading)
//    val state: StateFlow<ProblemaState> = _state

    fun marcarComoResuelto(problema: Problema) {
        viewModelScope.launch {
        val problemaEntity =  problema.toEntity()

            val updatedProblema = problemaEntity.copy(resuelto = true, usuarioQueValida = usuarioActual)

            updateProblemaUseCase(updatedProblema)
            Log.d("MarcarProblemaResueltoViewModel", "Problema actualizado: $updatedProblema")

        }
    }


    sealed class ProblemaState {
        data class Success(val problema: Problema) : ProblemaState()
        object Loading : ProblemaState()
        data class Error(val message: String) : ProblemaState()
    }

}



