package com.empresa.aplicacion.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao

//copiado codelab SQLDemo
    companion object {
        @Volatile //para que los cambios se reflejen inmediatamente en otros hilos
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "baseDatosMejoras"
                )
                  //  .createFromAsset("database/usuariosDao.db")

                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}