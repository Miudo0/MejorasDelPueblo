package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ProblemasRepository
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import javax.inject.Inject

class DeleteProblemasUseCase @Inject constructor(
    private val repository: ProblemasRepository
) {
    suspend operator fun invoke (problema: Problemas) {
        repository.deleteProblema(problema)
    }

}