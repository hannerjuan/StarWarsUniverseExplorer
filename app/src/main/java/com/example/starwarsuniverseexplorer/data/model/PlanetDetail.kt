package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

data class PlanetDetail(
    val name: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerializedName("surface_water") val surfaceWater: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val url: String
)
