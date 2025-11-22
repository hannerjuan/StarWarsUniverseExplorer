package com.example.starwarsuniverseexplorer.data.remote

import com.example.starwarsuniverseexplorer.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitClient.kt en la raíz del proyecto
object RetrofitClient {

    // "lazy" solo se crea la primera vez que se llama, ahorrando memoria.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Para convertir JSON a Kotlin
            .build()
    }

    //
    val apiService: StarWarsApiService by lazy {
        retrofit.create(StarWarsApiService::class.java)
    }
}
