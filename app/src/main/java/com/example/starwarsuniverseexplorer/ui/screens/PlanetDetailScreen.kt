package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.starwarsuniverseexplorer.ui.components.ErrorComponent
import com.example.starwarsuniverseexplorer.ui.components.LoadingComponent
import com.example.starwarsuniverseexplorer.viewmodel.PlanetDetailUiState
import com.example.starwarsuniverseexplorer.viewmodel.PlanetDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.ViewModelFactory

@Composable
fun PlanetDetailScreen(planetId: String) {
    val viewModel: PlanetDetailViewModel = viewModel(factory = ViewModelFactory())
    viewModel.fetchPlanetDetails(planetId)

    val uiState by viewModel.uiState.collectAsState()

    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            when (val state = uiState) {
                is PlanetDetailUiState.Loading -> LoadingComponent()
                is PlanetDetailUiState.Success -> PlanetDetails(planet = state.planet)
                is PlanetDetailUiState.Error -> ErrorComponent(message = state.message)
            }
        }
    }
}

@Composable
fun PlanetDetails(planet: com.example.starwarsuniverseexplorer.data.model.PlanetDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = planet.name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow(label = "Diameter:", value = planet.diameter)
                InfoRow(label = "Climate:", value = planet.climate)
                InfoRow(label = "Terrain:", value = planet.terrain)
                InfoRow(label = "Gravity:", value = planet.gravity)
                InfoRow(label = "Population:", value = planet.population)
                InfoRow(label = "Surface Water:", value = planet.surfaceWater)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = "$label ", style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
