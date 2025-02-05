package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ProblemasRepository
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MostrarProblemasUseCase @Inject constructor(
    private val repository: ProblemasRepository

) {
    suspend operator fun invoke(tipo: String): List<Problemas> {
        return withContext(IO) {
            repository.getProblemasByTipo(tipo = tipo)
        }
    }
}