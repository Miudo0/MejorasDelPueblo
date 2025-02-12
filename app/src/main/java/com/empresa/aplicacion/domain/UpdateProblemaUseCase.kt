package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ProblemasRepository
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import javax.inject.Inject

class UpdateProblemaUseCase @Inject constructor(
    private val problemasRepository: ProblemasRepository
){
    suspend operator fun invoke(problema: Problemas) {
        problemasRepository.updateProblema(problema)
    }
}