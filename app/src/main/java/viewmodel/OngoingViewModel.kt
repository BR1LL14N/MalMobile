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

    private val _animeList = MutableStateFlow<List<AnimeItem>>(emptyList())
    val animeList: StateFlow<List<AnimeItem>> = _animeList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        Log.d(TAG, "ViewModel dibuat, memulai fetch data...")
        fetchOngoingAnime()
    }

    private fun fetchOngoingAnime() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Panggilan ini sudah benar sesuai ApiService baru
                val response = RetrofitClient.apiService.getOngoingAnime(
                    type = "ongoing",
                    page = 1
                )

                // Logika ini sudah benar sesuai API baru
                if (response.success) {
                    _animeList.value = response.result
                    Log.d(TAG, "Berhasil fetch data: ${response.result.size} item.")
                } else {
                    Log.e(TAG, "API Error: Code ${response.code}")
                    _animeList.value = emptyList()
                }

            } catch (e: IOException) {
                Log.e(TAG, "Network Error: ${e.message}")
            } catch (e: Exception) {
                Log.e(TAG, "General Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}