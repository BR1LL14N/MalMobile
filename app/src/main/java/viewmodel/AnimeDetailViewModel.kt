package viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import models.AnimeDetailResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import network.RetrofitClient
import com.example.myanime.navigation.AnimeDetailScreen

class AnimeDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "DetailViewModel"

    // Ambil 'slug' yang dikirim dari navigasi
    private val animeSlug: String = checkNotNull(savedStateHandle[AnimeDetailScreen.ARG_SLUG])

    // State untuk menampung hasil detail
    private val _animeDetail = MutableStateFlow<AnimeDetailResult?>(null)
    val animeDetail: StateFlow<AnimeDetailResult?> = _animeDetail.asStateFlow()

    // State untuk loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        Log.d(TAG, "ViewModel dibuat untuk slug: $animeSlug")
        fetchAnimeDetail()
    }

    private fun fetchAnimeDetail() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.apiService.getAnimeDetail(slug = animeSlug)
                if (response.success) {
                    _animeDetail.value = response.result
                    Log.d(TAG, "Berhasil fetch detail: ${response.result.title}")
                } else {
                    Log.e(TAG, "API Error: Code ${response.code}")
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