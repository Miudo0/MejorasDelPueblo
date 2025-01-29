package com.empresa.aplicacion.ui.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.network.ChucknorrisApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val chuckNorrisApi: ChucknorrisApi
): ViewModel() {

    private var _state = MutableStateFlow<String?>(null)
    val state = _state

    init {
        viewModelScope.launch {
            try {
                val joke = chuckNorrisApi.getRandomJoke()
                Log.d("API", "Chiste recibido: ${joke.value}")
                _state.value = joke.value
            } catch (e: Exception) {
                Log.e("API", "Error al obtener el chiste", e)
            }
        }
    }
}




