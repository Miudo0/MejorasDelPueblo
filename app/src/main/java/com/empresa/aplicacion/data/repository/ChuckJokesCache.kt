package com.empresa.aplicacion.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.empresa.aplicacion.data.room.chuckJokesDatabase.ChuckJokeDao
import com.empresa.aplicacion.data.room.chuckJokesDatabase.toDomain
import com.empresa.aplicacion.data.room.chuckJokesDatabase.toEntity
import com.empresa.aplicacion.domain.ChuckJoke
import javax.inject.Inject

class ChuckJokesCache @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dao: ChuckJokeDao
) {
    suspend fun getRandomJoke(): ChuckJoke {
//        return if (sharedPreferences.getString("joke", null) != null) {
//            dao.getAll().random().toDomain()
//        } else {
//            null
//        }

        val jokes = dao.getAll()
        return if (jokes.isNotEmpty()) {
            jokes.random().toDomain()
        } else {
            ChuckJoke("Error", "No hay chistes disponibles")
        }
    }
    suspend fun saveJoke(joke: ChuckJoke) {
        dao.insert(joke.toEntity())
        Log.d("cache", "joke saved")


    }

}


