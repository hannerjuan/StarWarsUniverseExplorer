package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.starwarsuniverseexplorer.data.model.Film
import com.example.starwarsuniverseexplorer.ui.components.ErrorComponent
import com.example.starwarsuniverseexplorer.ui.components.FilmListItem
import com.example.starwarsuniverseexplorer.ui.components.LoadingComponent
import com.example.starwarsuniverseexplorer.viewmodel.FilmsUiState
import com.example.starwarsuniverseexplorer.viewmodel.FilmsViewModel
import com.example.starwarsuniverseexplorer.viewmodel.ViewModelFactory

@Composable
fun FilmListScreen(onFilmClick: (String) -> Unit) {
    val viewModel: FilmsViewModel = viewModel(factory = ViewModelFactory())
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is FilmsUiState.Loading -> LoadingComponent()
        is FilmsUiState.Success -> FilmList(films = state.films, onFilmClick = onFilmClick)
        is FilmsUiState.Error -> ErrorComponent(message = state.message)
    }
}

@Composable
fun FilmList(films: List<Film>, onFilmClick: (String) -> Unit) {
    LazyColumn(
        // Añade un padding de 16 dp en la parte superior y deja los demás en 0.
        contentPadding = PaddingValues(top = 40.dp)
    ) {
        items(films) { film ->
            FilmListItem(film = film, onItemClick = onFilmClick)
        }
    }
}
