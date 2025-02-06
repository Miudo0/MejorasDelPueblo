package com.empresa.aplicacion.data.room.ChuckJokesDatabase

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [ChuckJokeEntity::class], version = 1)
abstract class ChuckJokesDatabase : RoomDatabase() {
    abstract fun ChuckJokeDao(): ChuckJokeDao
}

