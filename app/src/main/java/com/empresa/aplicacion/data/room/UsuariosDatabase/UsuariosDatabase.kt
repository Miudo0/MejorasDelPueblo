package com.empresa.aplicacion.data.room.UsuariosDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 2)
abstract class UsuariosDatabase : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao


}