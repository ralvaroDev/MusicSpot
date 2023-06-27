package pe.codex.musicspot.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavData(
    val title: Navigation,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavData(Navigation.Home, Icons.Outlined.Home),
    BottomNavData(Navigation.Search, Icons.Outlined.Search),
    BottomNavData(Navigation.Library, Icons.Outlined.LibraryMusic),
    BottomNavData(Navigation.Profile, Icons.Outlined.AccountCircle)
)