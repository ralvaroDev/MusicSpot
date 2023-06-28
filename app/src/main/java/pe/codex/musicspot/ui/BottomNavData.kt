package pe.codex.musicspot.ui

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.LibraryMusic
import pe.codex.musicspot.R

data class BottomNavData(
    val title: Navigation,
    @DrawableRes val icon: Int
)

val bottomNavItems = listOf(
    BottomNavData(Navigation.Home, R.drawable.ic_home),
    BottomNavData(Navigation.Search, R.drawable.ic_search),
    BottomNavData(Navigation.Library, R.drawable.ic_stacked_books),
    BottomNavData(Navigation.Profile, R.drawable.ic_profile)
)