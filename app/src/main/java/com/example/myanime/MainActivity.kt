package com.example.myanime

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import network.RetrofitClient
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.OngoingAnimeScreen
import viewmodel.OngoingViewModel
import ui.theme.MyAnimeTheme

class MainActivity : ComponentActivity() { // <-- GANTI DARI AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent adalah pintu masuk ke Jetpack Compose
        setContent {
            // 'MyAnimeTheme' adalah tema default project Anda.
            // Nama ini mungkin berbeda (misal: 'NamaProjectAndaTheme')
            // Cek di package 'ui.theme' Anda.
            MyAnimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Panggil fungsi utama aplikasi kita
                    AnimeApp()
                }
            }
        }
    }
}

@Composable
fun AnimeApp() {
    // 1. Dapatkan instance ViewModel
    //    Compose akan otomatis mengelola siklus hidup ViewModel ini
    val viewModel: OngoingViewModel = viewModel()

    // 2. Ambil 'state' (daftar anime dan status loading) dari ViewModel.
    //    'collectAsState' membuat Composable ini "mendengarkan" perubahan.
    //    Setiap kali data di ViewModel berubah, UI akan otomatis di-update.
    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // 3. Tampilkan UI Screen kita, berikan data yang sudah didapat
    OngoingAnimeScreen(
        animeList = animeList,
        isLoading = isLoading
    )
}