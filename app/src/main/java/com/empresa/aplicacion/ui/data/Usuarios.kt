package com.empresa.aplicacion.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


    @Entity
    data class Usuario(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "username") val username: String?,
        @ColumnInfo(name = "pass") val pass: String?
    )
