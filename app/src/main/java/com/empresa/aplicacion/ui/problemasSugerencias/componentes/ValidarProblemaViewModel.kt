package com.empresa.aplicacion.ui.problemasSugerencias.componentes

import androidx.lifecycle.ViewModel
import com.empresa.aplicacion.domain.GetUserSharedPreferencesUseCase
import com.empresa.aplicacion.domain.Problema
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ValidarProblemaViewModel @Inject constructor(

    sharedPreferencesUseCase: GetUserSharedPreferencesUseCase
) : ViewModel() {
    val usuarioActual = sharedPreferencesUseCase.getUserFromSharedPreferences()

    fun mostrarBotonEliminar(problema: Problema): Boolean {
        return problema.username == usuarioActual

    }

    fun mostrarProblemaSolucionado(problema: Problema): Boolean {
        return !problema.resuelto && usuarioActual != problema.username
    }

    fun confirmarProblemaSolucionado(problema: Problema): Boolean {
        return problema.resuelto && usuarioActual != problema.username && usuarioActual != problema.usuarioQueValida
    }

}