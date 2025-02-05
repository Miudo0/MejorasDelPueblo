package com.empresa.aplicacion.data.room.NotificacionesDatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [AlertaNotificaciones::class], version = 1)
    abstract class NotificacionesDatabase : RoomDatabase() {
        abstract fun notificacionesDao(): NotificacionesDao
    }

