package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    // Column untuk menengahkan konten secara vertikal dan horizontal
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ikon untuk Halaman Profile
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "Halaman Profile",
            modifier = Modifier.size(80.dp),
            tint = Color.Gray // Beri sedikit warna
        )

        Spacer(modifier = Modifier.height(16.dp)) // Jarak

        // Teks Judul
        Text(
            text = "Profil Pengguna",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp)) // Jarak

        // Teks Deskripsi
        Text(
            text = "Pengaturan akun dan preferensi Anda.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}