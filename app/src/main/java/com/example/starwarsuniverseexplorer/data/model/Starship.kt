package com.example.starwarsuniverseexplorer.data.model

// Modelo para la respuesta de la lista de naves espaciales
data class StarshipListResponse(
    val results: List<Starship>
)

// Modelo para una única nave espacial
data class Starship(
    val name: String,
    val url: String
)
