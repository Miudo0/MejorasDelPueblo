package com.empresa.aplicacion.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET

//https://api.chucknorris.io/jokes/random
interface ChucknorrisApi {
    @GET("jokes/random")
    suspend fun getRandomJoke(): RandomJokeResponse
}

@Serializable
data class RandomJokeResponse(
    @SerialName("created_at") val createdAt: String,
    val value: String,

    )