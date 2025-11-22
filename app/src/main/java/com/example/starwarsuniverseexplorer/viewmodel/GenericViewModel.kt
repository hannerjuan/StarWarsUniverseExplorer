package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Estado de la UI genérico para listas. Usamos 'out T' para la covarianza.
sealed class GenericUiState<out T> {
    data object Loading : GenericUiState<Nothing>()
    data class Success<T>(val items: List<T>) : GenericUiState<T>()
    data class Error(val message: String) : GenericUiState<Nothing>()
}

// Hacemos el ViewModel genérico. Recibe una función "fetcher" que sabe cómo obtener los datos.
class GenericViewModel<T>(
    private val fetcher: suspend () -> List<T>
) : ViewModel() {

    // Un único StateFlow genérico para el estado de la UI
    private val _uiState = MutableStateFlow<GenericUiState<T>>(GenericUiState.Loading)
    val uiState: StateFlow<GenericUiState<T>> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _uiState.value = GenericUiState.Loading
            try {
                // Llamamos a la función fetcher
                val items = fetcher()
                _uiState.value = GenericUiState.Success(items)
            } catch (e: Exception) {
                _uiState.value = GenericUiState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}
