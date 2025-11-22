package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

// Modelo para la respuesta de la lista de personajes
data class PeopleListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Person>
)

// Modelo para un único personaje
data class Person(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    @SerializedName("homeworld")
    val homeworldUrl: String,
    @SerializedName("films")
    val filmUrls: List<String>,
    val url: String // Para extraer el ID
)
