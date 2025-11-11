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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.navigation.navArgument
import com.example.myanime.ui.screens.FavoriteScreen
import ui.screens.AnimeDetailScreen
import ui.screens.HomeScreen
import ui.screens.ProfileScreen
import ui.screens.SearchScreen
import ui.screens.WatchScreen


@Composable
fun MainApp() {
    val navController = rememberNavController()

    // Daftar item navbar kita
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Favorite,
        BottomBarScreen.Profile
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
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route, // Mulai dari Home
            modifier = Modifier.padding(innerPadding)
        ) {
            // --- UBAH PANGGILAN INI ---
            composable(BottomBarScreen.Home.route) {
                // Kita berikan navController ke HomeScreen
                HomeScreen(navController = navController)
            }
            composable(BottomBarScreen.Search.route) { SearchScreen() }
            composable(BottomBarScreen.Favorite.route) { FavoriteScreen() }
            composable(BottomBarScreen.Profile.route) { ProfileScreen() }

            // --- TAMBAHKAN RUTE BARU INI ---
            composable(
                route = AnimeDetailScreen.route,
                arguments = listOf(
                    navArgument(AnimeDetailScreen.ARG_SLUG) { type = NavType.StringType }
                )
            ) {
                // --- PERBARUI INI: Berikan NavController ---
                AnimeDetailScreen(navController = navController)
            }

            composable(
                route = WatchScreen.route,
                arguments = listOf(
                    navArgument(WatchScreen.ARG_SLUG) { type = NavType.StringType }
                )
            ) {
                WatchScreen()
            }
        }
    }
}



