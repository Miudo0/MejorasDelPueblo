package com.empresa.aplicacion.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


    @Entity
    data class Usuario(
        @PrimaryKey(autoGenerate = true) val uid: Int=0,
        @ColumnInfo(name = "username") val username: String?,
        @ColumnInfo(name = "pass") val pass: String?,
        @ColumnInfo(name = "email") val email: String?
    )
