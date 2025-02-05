package com.empresa.aplicacion.data.room.NotificacionesDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class AlertaNotificaciones(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
)
