package ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.OngoingAnimeScreen
import viewmodel.OngoingViewModel


@Composable
fun HomeScreen() {

    val viewModel: OngoingViewModel = viewModel()

    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    OngoingAnimeScreen(
        animeList = animeList,
        isLoading = isLoading
    )
}