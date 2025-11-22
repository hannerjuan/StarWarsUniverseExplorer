package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.Person
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

// Estado de la UI para la lista de personajes
sealed class PeopleUiState {
    object Loading : PeopleUiState()
    data class Success(val people: List<Person>) : PeopleUiState()
    data class Error(val message: String) : PeopleUiState()
}

class PeopleViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PeopleUiState>(PeopleUiState.Loading)
    val uiState: StateFlow<PeopleUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allPeople = MutableStateFlow<List<Person>>(emptyList())

    init {
        fetchPeople()
        observeFilteredPeople()
    }

    private fun fetchPeople() {
        viewModelScope.launch {
            _uiState.value = PeopleUiState.Loading
            try {
                val response = repository.getPeople()
                _allPeople.value = response.results
            } catch (e: Exception) {
                _uiState.value = PeopleUiState.Error("Error fetching people: ${e.message}")
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    private fun observeFilteredPeople() {
        viewModelScope.launch {
            combine(_allPeople, _searchQuery) { people, query ->
                if (query.isBlank()) {
                    people
                } else {
                    people.filter { it.name.contains(query, ignoreCase = true) }
                }
            }.collect { filteredPeople ->
                _uiState.value = PeopleUiState.Success(filteredPeople)
            }
        }
    }
}
