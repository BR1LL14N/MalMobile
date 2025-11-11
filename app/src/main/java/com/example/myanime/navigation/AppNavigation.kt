package com.example.myanime.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myanime.ui.screens.FavoriteScreen
import ui.screens.HomeScreen
import ui.screens.ProfileScreen
import ui.screens.SearchScreen


@Composable
fun MainApp() {
    val navController = rememberNavController()

    // Daftar item navbar kita
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Favorite,
        Screen.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up ke start destination agar tombol back
                                // tidak menumpuk stack
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Hindari duplikasi saat di-klik ulang
                                launchSingleTop = true
                                // Restore state saat navigasi kembali
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost adalah area konten yang akan berubah
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route, // Mulai dari Home
            modifier = Modifier.padding(innerPadding)
        ) {
            // Definisikan rute ke screen
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Favorite.route) { FavoriteScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}



