package com.empresa.aplicacion.data.repository

import com.empresa.aplicacion.data.network.ChucknorrisApi
import com.empresa.aplicacion.domain.ChuckJoke
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChuckJokesRepository @Inject constructor(
    private val api: ChucknorrisApi,
    private val cache: ChuckJokesCache
) {
    suspend fun getJoke(): ChuckJoke{
        return withContext(IO) {
            try {
                val response = api
                    .getRandomJoke()
                ChuckJoke(response.createdAt, response.value)
            } catch (e: Exception) {
                cache.getRandomJoke()
            }

        }
    }
    suspend fun saveJoke(joke: ChuckJoke) {
        cache.saveJoke(joke)
    }

}