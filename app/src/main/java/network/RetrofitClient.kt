package network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    // -----------------------------------------------------------------
    // PENTING: Ganti BASE_URL sesuai setup Anda
    // -----------------------------------------------------------------
    // - Jika pakai Emulator Android: "http://10.0.2.2:3000/"
    // - Jika pakai HP Fisik (pastikan 1 WiFi): "http://192.168.1.10:3000/" (ganti IP-nya)
    // -----------------------------------------------------------------
    private const val BASE_URL = "https://api.ammaricano.my.id/"

    // 'lazy' berarti objek ini hanya akan dibuat saat pertama kali dibutuhkan
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Pakai Gson
            .build()
            .create(ApiService::class.java)
    }
}