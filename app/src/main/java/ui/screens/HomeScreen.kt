// app/src/main/java/com/example/myanime/ui/screens/HomeScreen.kt
package ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
// --- TAMBAHKAN IMPORT INI ---
import androidx.navigation.NavController
import ui.OngoingAnimeScreen
import viewmodel.OngoingViewModel


@Composable
fun HomeScreen(
    navController: NavController // <-- Tambahkan parameter ini
) {
    val viewModel: OngoingViewModel = viewModel()
    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    OngoingAnimeScreen(
        animeList = animeList,
        isLoading = isLoading,
        navController = navController // <-- Kirim ke UI
    )
}