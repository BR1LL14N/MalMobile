package com.example.myanime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteScreen() {
    // Column untuk menengahkan konten secara vertikal dan horizontal
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ikon untuk Halaman Favorit
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Halaman Favorit",
            modifier = Modifier.size(80.dp),
            tint = Color.Gray // Beri sedikit warna
        )

        Spacer(modifier = Modifier.height(16.dp)) // Jarak

        // Teks Judul
        Text(
            text = "Anime Favorit",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp)) // Jarak

        // Teks Deskripsi
        Text(
            text = "Daftar anime yang kamu sukai akan muncul di sini.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}