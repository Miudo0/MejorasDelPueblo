package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.ProblemasRepository
import com.empresa.aplicacion.data.room.problemasDatabase.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProblemasFlowConvertirUseCase @Inject constructor(
    private val repository: ProblemasRepository
) {
    operator fun invoke(tipo: String): Flow<List<Problema>> =
        repository.getByFlowTipoParaConvertir(tipo) // Ahora devuelve Flow<List<ProblemaEntity>>
            .map { problemasEntityList ->
                problemasEntityList.map { it.toDomain() } // Convierte cada ProblemaEntity a Problema
            }
            .flowOn(Dispatchers.IO)

}