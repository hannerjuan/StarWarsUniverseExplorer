package com.example.starwarsuniverseexplorer.data.model

// Modelo para la respuesta de la lista de planetas
data class PlanetListResponse(
    val results: List<Planet>
)

// Modelo para un único planeta
data class Planet(
    val name: String,
    val url: String
)
