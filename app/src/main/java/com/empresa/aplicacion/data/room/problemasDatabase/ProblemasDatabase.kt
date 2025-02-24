package com.empresa.aplicacion.data.room.problemasDatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ProblemasEntity::class], version = 2)
    abstract class ProblemasDatabase : RoomDatabase() {
        abstract fun problemasDao(): ProblemasDao


    }