package com.empresa.aplicacion.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao

}