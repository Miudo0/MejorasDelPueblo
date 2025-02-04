package com.empresa.aplicacion.data.repository

import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.data.room.ProblemasDatabase.ProblemasDatabase
import javax.inject.Inject

class ProblemasRepository @Inject constructor(
    private val problemasDatabase: ProblemasDatabase
) {
    suspend fun getProblemasByTipo(tipo: String): List<Problemas> {
        val problemasDao = problemasDatabase.problemasDao()
        return problemasDao.getByTipo(tipo)

    }
    suspend fun newProblema(titulo: String, descripcion: String, tipo: String){
        val problemasDao = problemasDatabase.problemasDao()
        val nuevoProblema = Problemas(titulo = titulo, descripcion = descripcion, tipo = tipo)
        problemasDao.insertAll(nuevoProblema)

    }
}