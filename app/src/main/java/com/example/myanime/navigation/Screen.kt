package com.example.myanime.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

// Kita ubah 'sealed class' menjadi 'interface' agar lebih fleksibel
interface AppScreen {
    val route: String
    val label: String
    val icon: ImageVector? // Ikon kini opsional (Detail screen tidak punya ikon navbar)
}

sealed class BottomBarScreen(
    override val route: String,
    override val label: String,
    override val icon: ImageVector // Wajib punya ikon
) : AppScreen {
    object Home : BottomBarScreen("home", "Beranda", Icons.Default.Home)
    object Search : BottomBarScreen("search", "Search", Icons.Default.Search)
    object Favorite : BottomBarScreen("favorite", "Favorit", Icons.Default.Favorite)
    object Profile : BottomBarScreen("profile", "Profile", Icons.Default.Person)
}

object AnimeDetailScreen : AppScreen {
    // Ini adalah rute dasarnya
    const val routeBase = "animeDetail"

    // Ini adalah argumen yang akan kita kirim (yaitu slug)
    const val ARG_SLUG = "animeSlug"

    // Ini adalah rute lengkap dengan argumen
    // Hasilnya akan menjadi: "animeDetail/{animeSlug}"
    override val route = "$routeBase/{$ARG_SLUG}"

    // Tidak perlu label atau ikon karena tidak di navbar
    override val label: String = "Detail"
    override val icon: ImageVector? = null

    // Fungsi bantuan untuk membuat rute navigasi
    fun createRoute(slug: String) = "$routeBase/$slug"
}

object WatchScreen : AppScreen {
    const val routeBase = "watch"
    const val ARG_SLUG = "episodeSlug"

    // Rute lengkap: "watch/{episodeSlug}"
    override val route = "$routeBase/{$ARG_SLUG}"

    override val label: String = "Watch"
    override val icon: ImageVector? = null

    // Fungsi bantuan
    fun createRoute(slug: String) = "$routeBase/$slug"
}