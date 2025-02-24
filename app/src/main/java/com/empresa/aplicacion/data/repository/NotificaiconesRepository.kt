package com.empresa.aplicacion.data.repository

import com.empresa.aplicacion.data.room.notificacionesDatabase.AlertaNotificaciones
import com.empresa.aplicacion.data.room.notificacionesDatabase.NotificacionesDatabase
import javax.inject.Inject

class NotificaiconesRepository @Inject constructor(
    private val notificacionesDatabase: NotificacionesDatabase
){
  suspend fun  getNotificaciones():List<AlertaNotificaciones>{
        val notificacionesDao = notificacionesDatabase.notificacionesDao()
        return notificacionesDao.getAll()
    }

}