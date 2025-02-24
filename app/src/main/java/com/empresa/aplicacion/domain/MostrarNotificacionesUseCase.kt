package com.empresa.aplicacion.domain

import com.empresa.aplicacion.data.repository.NotificaiconesRepository
import com.empresa.aplicacion.data.room.notificacionesDatabase.AlertaNotificaciones
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MostrarNotificacionesUseCase @Inject constructor(
    private val repository: NotificaiconesRepository
) {
    suspend operator fun invoke(): List<AlertaNotificaciones> {
        return withContext(IO) {
            repository.getNotificaciones()
        }
    }


}