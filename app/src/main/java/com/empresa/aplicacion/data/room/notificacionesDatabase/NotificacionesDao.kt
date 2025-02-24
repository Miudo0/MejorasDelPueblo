package com.empresa.aplicacion.data.room.notificacionesDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificacionesDao {

    @Query("SELECT * FROM AlertaNotificaciones")
    suspend fun getAll(): List<AlertaNotificaciones>


    @Insert
    suspend fun insertAll(vararg alertaNotificaciones: AlertaNotificaciones)
}