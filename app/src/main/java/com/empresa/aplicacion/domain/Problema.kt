package com.empresa.aplicacion.domain

import org.osmdroid.util.GeoPoint

data class Problema(
    val uid: Int,
    val titulo: String?,
    val descripcion: String?,
    val tipo: String?,
    val username: String?,
    val resuelto: Boolean = false,
    val usuarioQueValida: String?,
    val imagenUri: String?,
    val ubicacion: GeoPoint?

)