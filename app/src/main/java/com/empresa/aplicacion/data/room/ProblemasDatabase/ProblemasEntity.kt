package com.empresa.aplicacion.data.room.ProblemasDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.empresa.aplicacion.domain.Problema

@Entity
data class ProblemasEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int ,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "descripcion") val descripcion: String?,
    @ColumnInfo(name = "tipo") val tipo: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "resuelto") val resuelto: Boolean = false,
    @ColumnInfo(name = "usuarioQueValida") val usuarioQueValida: String? = null
)

fun ProblemasEntity.toDomain() = Problema(this.uid,this.titulo,this.descripcion,this.tipo,this.username,this.resuelto,this.usuarioQueValida)
fun Problema.toEntity() = ProblemasEntity(uid=this.uid,this.titulo, this.descripcion,this.tipo,this.username,this.resuelto,this.usuarioQueValida)