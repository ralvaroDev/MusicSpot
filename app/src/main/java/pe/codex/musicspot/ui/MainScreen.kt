@file:OptIn(ExperimentalMaterial3Api::class)

package pe.codex.musicspot.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api
@Composable
fun AppScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(onClickNavigation = { navController.navigate(it) }) }
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
        }
    }
}

@Composable
fun BottomNavBar(modifier: Modifier = Modifier, onClickNavigation: (String) -> Unit) {
    var selectedItem by remember {
        mutableStateOf(0)
    }
    NavigationBar(modifier = modifier) {
        bottomNavItems.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClickNavigation(navItem.title.name)
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.title.name
                    )
                },
                label = { Text(text = navItem.title.name) },
                alwaysShowLabel = false
            )
        }
    }
}

enum class Navigation {
    Home,
    Search,
    Library,
    Profile
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(onClickNavigation = {})
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    AppScreen()
}
