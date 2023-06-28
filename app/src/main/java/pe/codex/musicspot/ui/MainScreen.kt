@file:OptIn(ExperimentalMaterial3Api::class)

package pe.codex.musicspot.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.codex.musicspot.ui.components.BottomNavigationBar

@ExperimentalMaterial3Api
@Composable
fun AppScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(onClickNavigation = { navController.navigate(it) }) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Navigation.Home.name) {
                HomeScreen()
            }
            composable(route = Navigation.Search.name) {
                SearchScreen()
            }
            composable(route = Navigation.Library.name) {
                HomeScreen()
            }
            composable(route = Navigation.Profile.name) {
                SearchScreen()
            }
        }
    }
}

enum class Navigation {
    Home,
    Search,
    Library,
    Profile
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    AppScreen()
}
