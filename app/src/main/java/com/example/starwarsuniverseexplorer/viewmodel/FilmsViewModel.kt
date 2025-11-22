package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.Film
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Estado de la UI para la lista de películas
sealed class FilmsUiState {
    object Loading : FilmsUiState()
    data class Success(val films: List<Film>) : FilmsUiState()
    data class Error(val message: String) : FilmsUiState()
}

class FilmsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<FilmsUiState>(FilmsUiState.Loading)
    val uiState: StateFlow<FilmsUiState> = _uiState

    init {
        fetchFilms()
    }

    private fun fetchFilms() {
        viewModelScope.launch {
            _uiState.value = FilmsUiState.Loading
            try {
                val response = repository.getFilms()
                _uiState.value = FilmsUiState.Success(response.results)
            } catch (e: Exception) {
                _uiState.value = FilmsUiState.Error("Error fetching films: ${e.message}")
            }
        }
    }
}
