package com.example.myanime.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "Beranda", Icons.Default.Home)
    object Search : Screen("search", "Search", Icons.Default.Search)
    object Favorite : Screen("favorite", "Favorit", Icons.Default.Favorite)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}