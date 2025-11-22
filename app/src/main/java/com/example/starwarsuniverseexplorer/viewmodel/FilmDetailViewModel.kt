package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.Film
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Estado de la UI para el detalle de la película
sealed class FilmDetailUiState {
    object Loading : FilmDetailUiState()
    data class Success(val film: Film) : FilmDetailUiState()
    data class Error(val message: String) : FilmDetailUiState()
}

class FilmDetailViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<FilmDetailUiState>(FilmDetailUiState.Loading)
    val uiState: StateFlow<FilmDetailUiState> = _uiState

    fun fetchFilmDetails(filmId: String) {
        viewModelScope.launch {
            _uiState.value = FilmDetailUiState.Loading
            try {
                val film = repository.getFilmDetails(filmId)
                _uiState.value = FilmDetailUiState.Success(film)
            } catch (e: Exception) {
                _uiState.value = FilmDetailUiState.Error("Error fetching film details: ${e.message}")
            }
        }
    }
}
