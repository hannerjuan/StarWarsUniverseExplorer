package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.starwarsuniverseexplorer.data.model.Film
import com.example.starwarsuniverseexplorer.ui.components.ErrorComponent
import com.example.starwarsuniverseexplorer.ui.components.LoadingComponent
import com.example.starwarsuniverseexplorer.viewmodel.FilmDetailUiState
import com.example.starwarsuniverseexplorer.viewmodel.FilmDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.ViewModelFactory

@Composable
fun FilmDetailScreen(filmId: String) {
    val viewModel: FilmDetailViewModel = viewModel(factory = ViewModelFactory())
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(filmId) {
        viewModel.fetchFilmDetails(filmId)
    }

    when (val state = uiState) {
        is FilmDetailUiState.Loading -> LoadingComponent()
        is FilmDetailUiState.Success -> FilmDetails(film = state.film)
        is FilmDetailUiState.Error -> ErrorComponent(message = state.message)
    }
}

@Composable
fun FilmDetails(film: Film) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Title: ${film.title}")
        Text("Episode: ${film.episodeId}")
        Text("Director: ${film.director}")
        Text("Release Date: ${film.releaseDate}")
        Text("Opening Crawl: ${film.openingCrawl}")
    }
}
