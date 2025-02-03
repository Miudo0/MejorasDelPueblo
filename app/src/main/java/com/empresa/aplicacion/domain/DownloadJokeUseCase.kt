package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ChuckJokesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadJokeUseCase @Inject constructor(
    private val repository: ChuckJokesRepository
) {
    suspend operator fun invoke(): String {
        return withContext(IO) {
            repository.getJoke()
        }
    }
}