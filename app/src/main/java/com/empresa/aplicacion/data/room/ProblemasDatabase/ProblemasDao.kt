package com.empresa.aplicacion.data.room.ProblemasDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProblemasDao {
    @Query("SELECT * FROM Problemas")
    suspend fun getAll(): List<Problemas>

    @Query("SELECT * FROM Problemas WHERE tipo LIKE :tipo")
    suspend fun getByTipo(tipo: String): List<Problemas>

    @Insert
    suspend fun insertAll(vararg problema: Problemas)

}