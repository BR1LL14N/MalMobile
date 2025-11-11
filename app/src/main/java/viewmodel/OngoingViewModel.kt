package viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import network.RetrofitClient
import models.AnimeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class OngoingViewModel : ViewModel() {

    private val TAG = "OngoingViewModel"

    // _animeList (Private): Hanya bisa diubah oleh ViewModel ini.
    private val _animeList = MutableStateFlow<List<AnimeItem>>(emptyList())

    // animeList (Public): Hanya bisa "dibaca" oleh UI.
    val animeList: StateFlow<List<AnimeItem>> = _animeList.asStateFlow()

    // State untuk menunjukkan apakah sedang loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // 'init' block akan otomatis dijalankan saat ViewModel ini pertama kali dibuat.
    init {
        Log.d(TAG, "ViewModel dibuat, memulai fetch data...")
        fetchOngoingAnime()
    }

    private fun fetchOngoingAnime() {
        // Gunakan viewModelScope, yang otomatis terikat dengan siklus hidup ViewModel
        viewModelScope.launch {
            _isLoading.value = true // Mulai loading
            try {
                // Panggil API dengan type "ongoing" dan page 1
                val response = RetrofitClient.apiService.getOngoingAnime(
                    type = "ongoing",
                    page = 1
                )

                if (response.success) {
                    // Update 'state' kita dengan data baru
                    _animeList.value = response.result
                    Log.d(TAG, "Berhasil fetch data: ${response.result.size} item.")
                } else {
                    Log.e(TAG, "API Error: Code ${response.code}")
                    _animeList.value = emptyList() // Kosongkan jika error
                }

            } catch (e: IOException) {
                Log.e(TAG, "Network Error: ${e.message}")
            } catch (e: Exception) {
                Log.e(TAG, "General Error: ${e.message}")
            } finally {
                _isLoading.value = false // Selesai loading
            }
        }
    }
}