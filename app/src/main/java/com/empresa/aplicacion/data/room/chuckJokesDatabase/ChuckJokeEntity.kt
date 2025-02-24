package com.empresa.aplicacion.data.room.chuckJokesDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.empresa.aplicacion.domain.ChuckJoke
import kotlinx.serialization.SerialName


@Entity
class ChuckJokeEntity (
    @PrimaryKey
    @SerialName("created_at") val createdAt: String,
    val value: String,
)

fun ChuckJokeEntity.toDomain() = ChuckJoke(this.createdAt, this.value)
fun ChuckJoke.toEntity() = ChuckJokeEntity(this.createdAt, this.value)