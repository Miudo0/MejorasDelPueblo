package com.empresa.aplicacion.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao


}