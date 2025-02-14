package com.empresa.aplicacion.data.room.ProblemasDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ProblemasDao {
    @Query("SELECT * FROM ProblemasEntity")
    suspend fun getAll(): List<ProblemasEntity>

    @Query("SELECT * FROM ProblemasEntity WHERE tipo LIKE :tipo")
    suspend fun getByTipo(tipo: String): List<ProblemasEntity>

    @Insert
    suspend fun insertAll(vararg problema: ProblemasEntity)

    @Delete
    suspend  fun delete(problema: ProblemasEntity)


    @Query("SELECT * FROM ProblemasEntity WHERE tipo LIKE :tipo")
     fun getByFlowTipo(tipo: String): Flow<List<ProblemasEntity>>

    @Query("SELECT * FROM ProblemasEntity WHERE tipo LIKE :tipo ORDER BY resuelto ASC")
    fun getByFlowTipoParaConvertir(tipo: String): Flow<List<ProblemasEntity>>

    @Query("SELECT * FROM ProblemasEntity WHERE username LIKE :username ORDER BY resuelto DESC")
    fun getByFlowUsername(username: String): Flow<List<ProblemasEntity>>


    @Update
    suspend fun update(problema: ProblemasEntity)
}