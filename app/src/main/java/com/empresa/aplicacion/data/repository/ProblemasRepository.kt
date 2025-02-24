package com.empresa.aplicacion.data.repository

import com.empresa.aplicacion.data.room.problemasDatabase.ProblemasDatabase
import com.empresa.aplicacion.data.room.problemasDatabase.ProblemasEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProblemasRepository @Inject constructor(
    private val problemasDatabase: ProblemasDatabase
) {
    suspend fun newProblema(titulo: String, descripcion: String, tipo: String, username: String){
        val problemasDao = problemasDatabase.problemasDao()
        val nuevoProblema = ProblemasEntity(uid= 0,titulo = titulo, descripcion = descripcion, tipo = tipo, username = username)
        problemasDao.insertAll(nuevoProblema)
    }

    fun getByFlowTipo(tipo: String) = problemasDatabase.problemasDao().getByFlowTipo(tipo)

    fun getByFlowTipoParaConvertir(tipo: String): Flow<List<ProblemasEntity>> =
       problemasDatabase.problemasDao().getByFlowTipo(tipo)

    fun getByFlowUsername(username: String): Flow<List<ProblemasEntity>> =
        problemasDatabase.problemasDao().getByFlowUsername(username)


    suspend fun deleteProblema(problema: ProblemasEntity){
        val problemasDao = problemasDatabase.problemasDao()
        problemasDao.delete(problema)
    }

    suspend fun updateProblema(problema: ProblemasEntity){
        val problemasDao = problemasDatabase.problemasDao()
        problemasDao.update(problema)
    }

}