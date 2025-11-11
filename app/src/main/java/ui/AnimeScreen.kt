// app/src/main/java/com/example/myanime/ui/AnimeScreen.kt
package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.example.myanime.navigation.AnimeDetailScreen
import models.AnimeItem

@Composable
fun OngoingAnimeScreen(
    animeList: List<AnimeItem>,
    isLoading: Boolean,
    navController: NavController, // <-- Terima NavController
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(animeList) { anime ->
                    AnimeItemCard(
                        anime = anime,
                        onClick = {
                            // Saat diklik, navigasi ke layar detail
                            // menggunakan rute dan slug
                            navController.navigate(
                                AnimeDetailScreen.createRoute(anime.slug)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeItemCard(
    anime: AnimeItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit // <-- Tambahkan parameter onClick
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // <-- Buat kartu bisa diklik
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            // Gambar Thumbnail
            AsyncImage(
                // --- PERBAIKAN: Gunakan 'poster' ---
                model = anime.poster,
                contentDescription = anime.title,
                modifier = Modifier
                    .size(width = 80.dp, height = 110.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Kolom Teks
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                // --- PERBAIKAN: Gunakan 'currentEpisode' ---
                Text(
                    text = anime.currentEpisode,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))

                // --- PERBAIKAN: Gunakan 'releaseDay' ---
                Text(
                    text = "Update: ${anime.releaseDay}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}