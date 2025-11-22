package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

// Modelo para la respuesta de la lista de películas
data class FilmListResponse(
    val results: List<Film>
)

// Modelo para una única película
data class Film(
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val director: String,
    @SerializedName("characters")
    val characterUrls: List<String>,
    val url: String // Para extraer el ID
)
