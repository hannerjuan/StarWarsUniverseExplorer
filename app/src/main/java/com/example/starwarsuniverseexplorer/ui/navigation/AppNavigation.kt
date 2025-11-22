package com.example.starwarsuniverseexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarsuniverseexplorer.ui.screens.*
import com.example.starwarsuniverseexplorer.viewmodel.PeopleViewModel
import com.example.starwarsuniverseexplorer.viewmodel.ViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }

        // People Routes
        composable("people") {
            val viewModel: PeopleViewModel = viewModel(factory = ViewModelFactory())
            PeopleListScreen(
                onPersonClick = { personId ->
                    navController.navigate("people/$personId")
                },
                viewModel = viewModel
            )
        }
        composable(
            route = "people/{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.StringType })
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getString("personId")
            requireNotNull(personId) { "personId parameter wasn't found. Please make sure it's set!" }
            PeopleDetailScreen(personId = personId)
        }

        // Film Routes
        composable("films") {
            FilmListScreen(onFilmClick = { filmId ->
                navController.navigate("films/$filmId")
            })
        }
        composable(
            route = "films/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.StringType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getString("filmId")
            requireNotNull(filmId) { "filmId parameter wasn't found. Please make sure it's set!" }
            FilmDetailScreen(filmId = filmId)
        }

        // Planet Routes
        composable("planets") {
            GenericListScreen(listType = "planets", navController = navController)
        }
        composable(
            route = "planets/{planetId}",
            arguments = listOf(navArgument("planetId") { type = NavType.StringType })
        ) { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId")
            requireNotNull(planetId) { "planetId parameter wasn't found. Please make sure it's set!" }
            PlanetDetailScreen(planetId = planetId)
        }

        // Starships and Vehicles Routes
        composable("starships") {
            GenericListScreen(listType = "starships", navController = navController)
        }
        composable("vehicles") {
            GenericListScreen(listType = "vehicles", navController = navController)
        }
    }
}
