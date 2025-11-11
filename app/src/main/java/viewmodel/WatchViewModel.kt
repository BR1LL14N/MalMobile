package viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanime.navigation.WatchScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import models.EpisodeDetailResult
import network.RetrofitClient
import java.io.IOException

class WatchViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "WatchViewModel"
    private val episodeSlug: String = checkNotNull(savedStateHandle[WatchScreen.ARG_SLUG]) // Pastikan ARG_SLUG benar

    // Kita ganti 'videoUrl' menjadi 'streamPageUrl' agar lebih jelas
    private val _streamPageUrl = MutableStateFlow<String?>(null)
    val streamPageUrl: StateFlow<String?> = _streamPageUrl.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        Log.d(TAG, "ViewModel dibuat untuk episode slug: $episodeSlug")
        fetchStreamPageUrl()
    }

    private fun fetchStreamPageUrl() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Kita HANYA lakukan panggilan pertama
                val response = RetrofitClient.apiService.getEpisodeDetail(slug = episodeSlug)

                if (response.success) {
                    val result = response.result

                    // Coba ambil finalUrl kalau ada
                    val resolvedUrl = when {
                        result.finalUrl != null -> result.finalUrl
                        else -> result.streamUrl
                    }

                    _streamPageUrl.value = resolvedUrl
                    Log.d(TAG, "✅ URL untuk diputar: $resolvedUrl")
                } else {
                    Log.e(TAG, "API Error: Code ${response.code}")
                    _streamPageUrl.value = null
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