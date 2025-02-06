package com.empresa.aplicacion.domain

import kotlinx.serialization.SerialName

data class ChuckJoke(
    @SerialName("created_at") val createdAt: String,
    val value: String,
)

