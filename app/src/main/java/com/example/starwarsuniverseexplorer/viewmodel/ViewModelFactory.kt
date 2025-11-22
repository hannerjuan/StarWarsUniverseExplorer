package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsuniverseexplorer.data.remote.RetrofitClient
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = StarWarsRepository(RetrofitClient.apiService)
        return when {
            modelClass.isAssignableFrom(PeopleViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                PeopleViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PeopleDetailViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                PeopleDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FilmsViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                FilmsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FilmDetailViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                FilmDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PlanetDetailViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                PlanetDetailViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
