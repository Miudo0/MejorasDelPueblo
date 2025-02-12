package com.empresa.aplicacion.data.room.ProblemasDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ProblemasDao {
    @Query("SELECT * FROM Problemas")
    suspend fun getAll(): List<Problemas>

    @Query("SELECT * FROM Problemas WHERE tipo LIKE :tipo")
    suspend fun getByTipo(tipo: String): List<Problemas>

    @Insert
    suspend fun insertAll(vararg problema: Problemas)

    @Delete
    suspend  fun delete(problema: Problemas)


    @Query("SELECT * FROM Problemas WHERE tipo LIKE :tipo")
     fun getByFlowTipo(tipo: String): Flow<List<Problemas>>

    @Update
    suspend fun update(problema: Problemas)
}