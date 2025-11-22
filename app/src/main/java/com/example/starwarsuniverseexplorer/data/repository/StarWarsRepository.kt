package com.example.starwarsuniverseexplorer.data.repository

import com.example.starwarsuniverseexplorer.data.model.*
import com.example.starwarsuniverseexplorer.data.remote.StarWarsApiService

class StarWarsRepository(private val apiService: StarWarsApiService) {


    suspend fun getPeople(): PeopleListResponse {
        return apiService.getPeople()
    }

    suspend fun getPersonDetails(personId: String): Person {
        return apiService.getPersonDetails(personId)
    }

    suspend fun getFilms(): FilmListResponse {
        return apiService.getFilms()
    }

    suspend fun getFilmDetails(filmId: String): Film {
        return apiService.getFilmDetails(filmId)
    }

    suspend fun getPlanets(): PlanetListResponse {
        return apiService.getPlanets()
    }

    suspend fun getPlanetDetails(planetId: String): PlanetDetail {
        return apiService.getPlanetDetails(planetId)
    }

    suspend fun getStarships(): StarshipListResponse {
        return apiService.getStarships()
    }

    suspend fun getVehicles(): VehicleListResponse {
        return apiService.getVehicles()
    }
}
