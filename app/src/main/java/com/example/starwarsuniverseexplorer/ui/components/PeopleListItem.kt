package com.example.starwarsuniverseexplorer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsuniverseexplorer.data.model.Person

@Composable
fun PeopleListItem(person: Person, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onItemClick(person.url.split("/").dropLast(1).last()) }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = person.name)
        }
    }
}
