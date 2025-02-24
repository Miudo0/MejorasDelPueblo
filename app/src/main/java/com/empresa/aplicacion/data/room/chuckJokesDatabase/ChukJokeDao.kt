package com.empresa.aplicacion.data.room.chuckJokesDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChuckJokeDao {
    @Query("SELECT * FROM ChuckJokeEntity")
    suspend fun getAll(): List<ChuckJokeEntity>

    @Query("DELETE FROM ChuckJokeEntity")
    suspend fun deleteAll()


    @Insert
    suspend fun insert(joke: ChuckJokeEntity)
}