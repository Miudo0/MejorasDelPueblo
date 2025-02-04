package com.empresa.aplicacion.data.room.ProblemasDatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Problemas::class], version = 1)
    abstract class ProblemasDatabase : RoomDatabase() {
        abstract fun problemasDao(): ProblemasDao


    }