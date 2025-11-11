package ui.screens

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import viewmodel.WatchViewModel
import viewmodel.AnimeDetailViewModel



@Composable
fun WatchScreen() {
    val viewModel: WatchViewModel = viewModel()

    // Ambil state dari ViewModel yang baru
    val isLoading by viewModel.isLoading.collectAsState()
    val streamPageUrl by viewModel.streamPageUrl.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        } else if (streamPageUrl != null) {
            // Jika URL halaman web ditemukan, tampilkan WebView
            VideoWebView(
                url = streamPageUrl!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f) // Paksa rasio 16:9
                    .align(Alignment.Center)
            )
        } else {
            // Jika gagal
            Text(
                text = "Gagal memuat halaman video.",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun VideoWebView(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                WebView.setWebContentsDebuggingEnabled(true) // untuk debugging iframe
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.mediaPlaybackRequiresUserGesture = false
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                settings.userAgentString =
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"

                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun onReceivedError(
                        view: WebView?,
                        errorCode: Int,
                        description: String?,
                        failingUrl: String?
                    ) {
                        Log.e("WebView", "Error $errorCode: $description ($failingUrl)")
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        Log.d("WebView", "✅ Halaman selesai dimuat: $url")
                    }
                }

                // Header referer penting agar Desustream mengizinkan video tampil
                loadUrl(
                    url,
                    mapOf("Referer" to "https://desustream.info")
                )
            }
        },
        modifier = modifier
    )
}
