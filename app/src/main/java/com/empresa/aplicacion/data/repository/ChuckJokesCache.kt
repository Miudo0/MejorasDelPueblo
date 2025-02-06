package com.empresa.aplicacion.data.repository

import android.util.Log
import com.empresa.aplicacion.data.room.ChuckJokesDatabase.ChuckJokeDao
import com.empresa.aplicacion.data.room.ChuckJokesDatabase.toDomain
import com.empresa.aplicacion.data.room.ChuckJokesDatabase.toEntity
import com.empresa.aplicacion.domain.ChuckJoke
import javax.inject.Inject

class ChuckJokesCache @Inject constructor(
//    private val sharedPreferences: SharedPreferences,
    private val dao: ChuckJokeDao
) {
    suspend fun getRandomJoke(): ChuckJoke {
//        return if (sharedPreferences.getString("joke", null) != null) {
//            dao.getAll().random().toDomain()
//        } else {
//            null
//        }
        return dao.getAll().random().toDomain()
    }
    suspend fun saveJoke(joke: ChuckJoke) {
        dao.insert(joke.toEntity())
        Log.d("cache", "joke saved")


    }

}


