package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ProblemasRepository
import javax.inject.Inject

class GetMostrarProblemasFlowUseCase @Inject constructor(
    private val repository: ProblemasRepository
) {
    operator fun invoke(tipo: String) =
        repository.getByFlowTipo(tipo)
    }
