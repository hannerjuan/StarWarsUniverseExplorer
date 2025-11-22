package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.Person
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Estado de la UI para el detalle del personaje
sealed class PeopleDetailUiState {
    object Loading : PeopleDetailUiState()
    data class Success(val person: Person) : PeopleDetailUiState()
    data class Error(val message: String) : PeopleDetailUiState()
}

class PeopleDetailViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PeopleDetailUiState>(PeopleDetailUiState.Loading)
    val uiState: StateFlow<PeopleDetailUiState> = _uiState

    fun fetchPersonDetails(personId: String) {
        viewModelScope.launch {
            _uiState.value = PeopleDetailUiState.Loading
            try {
                val person = repository.getPersonDetails(personId)
                _uiState.value = PeopleDetailUiState.Success(person)
            } catch (e: Exception) {
                _uiState.value = PeopleDetailUiState.Error("Error fetching person details: ${e.message}")
            }
        }
    }
}
