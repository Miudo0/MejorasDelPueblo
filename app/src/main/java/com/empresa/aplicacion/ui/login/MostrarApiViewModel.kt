package com.empresa.aplicacion.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.domain.ChuckJoke
import com.empresa.aplicacion.domain.DownloadJokeUseCase
import com.empresa.aplicacion.domain.SaveJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MostrarApiViewModel @Inject constructor(

    private val getRandomJoke: DownloadJokeUseCase,
    private val saveJoke: SaveJokesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<ApiState>(ApiState.Loading)
    val state = _state


    init {
        viewModelScope.launch {
            _state.value = ApiState.Loading
            try {
            _state.value = getRandomJoke().let { ApiState.Success(it) }
            } catch (e: Throwable) {
                Log.e("API", "Error al obtener el chiste", e)
                _state.value = ApiState.Error
            }
        }
    }
}

sealed interface ApiState {

    data object Loading : ApiState
    data class Success(val joke: ChuckJoke) : ApiState
    data object Error : ApiState
}





