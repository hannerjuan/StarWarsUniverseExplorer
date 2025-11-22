package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.starwarsuniverseexplorer.data.model.Planet
import com.example.starwarsuniverseexplorer.data.model.Starship
import com.example.starwarsuniverseexplorer.data.model.Vehicle
import com.example.starwarsuniverseexplorer.data.remote.RetrofitClient
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.ui.components.ErrorComponent
import com.example.starwarsuniverseexplorer.ui.components.LoadingComponent
import com.example.starwarsuniverseexplorer.utils.extractIdFromUrl
import com.example.starwarsuniverseexplorer.viewmodel.GenericUiState
import com.example.starwarsuniverseexplorer.viewmodel.GenericViewModel


@Suppress("UNCHECKED_CAST")
fun <T> createGenericViewModelFactory(fetcher: suspend () -> List<T>): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <V : ViewModel> create(modelClass: Class<V>): V {
            return GenericViewModel(fetcher) as V
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericListScreen(listType: String, navController: NavController) {
    val repository = StarWarsRepository(RetrofitClient.apiService)

    val factory = when (listType) {
        "planets" -> createGenericViewModelFactory { repository.getPlanets().results }
        "starships" -> createGenericViewModelFactory { repository.getStarships().results }
        "vehicles" -> createGenericViewModelFactory { repository.getVehicles().results }
        else -> throw IllegalArgumentException("Unknown list type: $listType")
    }

    val viewModel: GenericViewModel<Any> = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = listType.replaceFirstChar { it.uppercase() }) })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is GenericUiState.Loading -> LoadingComponent()
                is GenericUiState.Success<*> -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.items) { item ->
                            val (displayText, onClickAction) = when (item) {
                                is Planet -> item.name to {
                                    val id = extractIdFromUrl(item.url)
                                    navController.navigate("planets/$id")
                                }

                                is Starship -> item.name to {
                                    val id = extractIdFromUrl(item.url)
                                    navController.navigate("starships/$id")
                                }

                                is Vehicle -> item.name to {
                                    val id = extractIdFromUrl(item.url)
                                    navController.navigate("vehicles/$id")
                                }

                                is String -> item to null
                                else -> "Unknown Item" to null
                            }
                            GenericListItem(item = displayText, onClick = onClickAction)
                        }
                    }
                }

                is GenericUiState.Error -> ErrorComponent(message = state.message)
            }
        }
    }
}

@Composable
fun GenericListItem(item: String, onClick: (() -> Unit)?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            )
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}