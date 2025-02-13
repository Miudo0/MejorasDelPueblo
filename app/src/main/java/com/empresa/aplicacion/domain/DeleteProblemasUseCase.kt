package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ProblemasRepository
import com.empresa.aplicacion.data.room.ProblemasDatabase.ProblemasEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteProblemasUseCase @Inject constructor(
    private val repository: ProblemasRepository
) {
    suspend operator fun invoke (problema: ProblemasEntity) {
        return withContext(IO) {
            repository.deleteProblema(problema)
        }
    }

}