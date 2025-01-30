package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ApiRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    suspend operator fun invoke(): String {
        return withContext(IO) {
            repository.getJoke()
        }
    }
}