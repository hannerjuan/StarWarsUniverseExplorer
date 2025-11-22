package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsuniverseexplorer.data.model.Person
import com.example.starwarsuniverseexplorer.ui.components.ErrorComponent
import com.example.starwarsuniverseexplorer.ui.components.LoadingComponent
import com.example.starwarsuniverseexplorer.ui.components.PeopleListItem
import com.example.starwarsuniverseexplorer.viewmodel.PeopleUiState
import com.example.starwarsuniverseexplorer.viewmodel.PeopleViewModel

@Composable
fun PeopleListScreen(onPersonClick: (String) -> Unit, viewModel: PeopleViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            label = { Text("Search by name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        when (val state = uiState) {
            is PeopleUiState.Loading -> LoadingComponent()
            is PeopleUiState.Success -> {
                PeopleList(people = state.people, onPersonClick = onPersonClick)
            }
            is PeopleUiState.Error -> ErrorComponent(message = state.message)
        }
    }
}

@Composable
fun PeopleList(people: List<Person>, onPersonClick: (String) -> Unit) {
    LazyColumn {
        items(people) { person ->
            PeopleListItem(person = person, onItemClick = onPersonClick)
        }
    }
}
