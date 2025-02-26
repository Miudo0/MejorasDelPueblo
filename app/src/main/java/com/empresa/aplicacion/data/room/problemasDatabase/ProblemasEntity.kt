package com.empresa.aplicacion.data.room.problemasDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.empresa.aplicacion.domain.Problema
import org.osmdroid.util.GeoPoint

@Entity
data class ProblemasEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "descripcion") val descripcion: String?,
    @ColumnInfo(name = "tipo") val tipo: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "resuelto") val resuelto: Boolean = false,
    @ColumnInfo(name = "usuarioQueValida") val usuarioQueValida: String? = null,
    @ColumnInfo(name = "imagenUri") val imagenUri: String? = null,
    @ColumnInfo(name = "latitud") val latitud: Double? = null,
    @ColumnInfo(name = "longitud") val longitud: Double? = null,

    )

fun ProblemasEntity.toDomain() =
    Problema(
        uid,
        titulo,
        descripcion,
        tipo,
        username,
        resuelto,
        usuarioQueValida,
        imagenUri,
        GeoPoint(latitud ?: 0.0, longitud ?: 0.0)
    )

fun Problema.toEntity() =
    ProblemasEntity(
        uid,
        titulo,
        descripcion,
        tipo,
        username,
        resuelto,
        usuarioQueValida,
        imagenUri,
        ubicacion?.latitude,
        ubicacion?.longitude
    )



