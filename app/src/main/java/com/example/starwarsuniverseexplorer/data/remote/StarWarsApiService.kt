package com.example.starwarsuniverseexplorer.data.remote

import com.example.starwarsuniverseexplorer.data.model.Film
import com.example.starwarsuniverseexplorer.data.model.FilmListResponse
import com.example.starwarsuniverseexplorer.data.model.PeopleListResponse
import com.example.starwarsuniverseexplorer.data.model.Person
import com.example.starwarsuniverseexplorer.data.model.PlanetDetail
import com.example.starwarsuniverseexplorer.data.model.PlanetListResponse
import com.example.starwarsuniverseexplorer.data.model.StarshipListResponse
import com.example.starwarsuniverseexplorer.data.model.VehicleListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface
StarWarsApiService {

    //endpoints del app
    @GET("people/")
    suspend fun getPeople(): PeopleListResponse

    @GET("people/{id}/")
    suspend fun getPersonDetails(@Path("id") personId: String): Person

    @GET("films/")
    suspend fun getFilms(): FilmListResponse

    @GET("films/{id}/")
    suspend fun getFilmDetails(@Path("id") filmId: String): Film

    @GET("planets/")
    suspend fun getPlanets(): PlanetListResponse

    @GET("planets/{id}/")
    suspend fun getPlanetDetails(@Path("id") planetId: String): PlanetDetail

    @GET("starships/")
    suspend fun getStarships(): StarshipListResponse

    @GET("vehicles/")
    suspend fun getVehicles(): VehicleListResponse
}
