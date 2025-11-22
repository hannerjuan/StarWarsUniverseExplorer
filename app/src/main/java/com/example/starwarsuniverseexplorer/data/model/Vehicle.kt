package com.example.starwarsuniverseexplorer.data.model

// Modelo para la respuesta de la lista de vehículos
data class VehicleListResponse(
    val results: List<Vehicle>
)

// Modelo para un único vehículo
data class Vehicle(
    val name: String,
    val url: String
)
