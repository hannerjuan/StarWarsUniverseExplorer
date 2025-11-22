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
import com.example.starwarsuniverseexplorer.data.model.Person
import com.example.starwarsuniverseexplorer.ui.components.ErrorComponent
import com.example.starwarsuniverseexplorer.ui.components.LoadingComponent
import com.example.starwarsuniverseexplorer.viewmodel.PeopleDetailUiState
import com.example.starwarsuniverseexplorer.viewmodel.PeopleDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.ViewModelFactory

@Composable
fun PeopleDetailScreen(personId: String) {
    val viewModel: PeopleDetailViewModel = viewModel(factory = ViewModelFactory())
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(personId) {
        viewModel.fetchPersonDetails(personId)
    }

    when (val state = uiState) {
        is PeopleDetailUiState.Loading -> LoadingComponent()
        is PeopleDetailUiState.Success -> PersonDetails(person = state.person)
        is PeopleDetailUiState.Error -> ErrorComponent(message = state.message)
    }
}

@Composable
fun PersonDetails(person: Person) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Name: ${person.name}")
        Text("Height: ${person.height}")
        Text("Mass: ${person.mass}")
        Text("Gender: ${person.gender}")
        // Aquí podemos añadir más detalles, como el planeta natal y las películas.
    }
}
