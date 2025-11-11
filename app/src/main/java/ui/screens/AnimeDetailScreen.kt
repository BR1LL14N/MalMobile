package ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.myanime.navigation.WatchScreen
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import models.AnimeDetailResult
import models.EpisodeItem
import viewmodel.AnimeDetailViewModel

@Composable
fun AnimeDetailScreen(
    navController: NavController // <-- TAMBAHKAN INI
) {
    val viewModel: AnimeDetailViewModel = viewModel()
    val animeDetail by viewModel.animeDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (animeDetail != null) {
            DetailContent(
                detail = animeDetail!!,
                navController = navController // <-- KIRIMKAN KE BAWAH
            )
        } else {
            Text(
                text = "Gagal memuat detail anime.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun DetailContent(detail: AnimeDetailResult,navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        // --- Bagian Header (Poster dan Info) ---
        item {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Poster
                AsyncImage(
                    model = detail.poster,
                    contentDescription = detail.title,
                    modifier = Modifier
                        .width(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth
                )
                // Info
                Column {
                    Text(
                        text = detail.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoRow("Skor", detail.score)
                    InfoRow("Status", detail.status)
                    InfoRow("Tipe", detail.tipe)
                    InfoRow("Studio", detail.studio)
                }
            }
        }

        // --- Bagian Sinopsis ---
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Sinopsis",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = detail.synopsis, style = MaterialTheme.typography.bodyMedium)
        }

        // --- Bagian Episode ---
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Daftar Episode",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Daftar Episode
        items(detail.episodes) { episode ->
            EpisodeCard(
                episode = episode,
                // --- TAMBAHKAN ONCLICK ---
                onClick = {
                    // Navigasi ke WatchScreen dengan slug episode
                    navController.navigate(
                        WatchScreen.createRoute(episode.slug)
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EpisodeCard(
    episode: EpisodeItem,
    onClick: () -> Unit // <-- PERBAIKAN: Hapus @Composable dari sini
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            // Tipe data 'onClick: () -> Unit' sekarang
            // cocok dengan yang diharapkan oleh .clickable
            .clickable(onClick = onClick)
    ) {
        Text(
            text = episode.episode,
            modifier = Modifier.padding(16.dp)
        )
    }
}
