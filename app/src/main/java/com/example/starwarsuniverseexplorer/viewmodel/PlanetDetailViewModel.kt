package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.PlanetDetail
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PlanetDetailUiState {
    object Loading : PlanetDetailUiState()
    data class Success(val planet: PlanetDetail) : PlanetDetailUiState()
    data class Error(val message: String) : PlanetDetailUiState()
}

class PlanetDetailViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PlanetDetailUiState>(PlanetDetailUiState.Loading)
    val uiState: StateFlow<PlanetDetailUiState> = _uiState

    fun fetchPlanetDetails(planetId: String) {
        viewModelScope.launch {
            _uiState.value = PlanetDetailUiState.Loading
            try {
                val planetDetails = repository.getPlanetDetails(planetId)
                _uiState.value = PlanetDetailUiState.Success(planetDetails)
            } catch (e: Exception) {
                _uiState.value = PlanetDetailUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
