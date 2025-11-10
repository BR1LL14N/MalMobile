package network

import retrofit2.http.GET
import retrofit2.http.Path
import models.AnimeApiResponse
import models.WelcomeResponse

interface ApiService {

    // Endpoint: http://localhost:3000/api/
    @GET("api/")
    suspend fun getWelcomeMessage(): WelcomeResponse

    // Endpoint: http://localhost:3000/api/complete
    @GET("api/complete")
    suspend fun getCompleteAnime(): AnimeApiResponse

    // Endpoint: http://localhost:3000/api/ongoing/page/1
    // Kita gunakan @Path untuk membuat angka '1' menjadi dinamis
    @GET("api/ongoing/page/{pageNumber}")
    suspend fun getOngoingAnime(
        @Path("pageNumber") page: Int
    ): AnimeApiResponse

    // TODO: Nanti Anda bisa tambahkan endpoint lain di sini
    // @GET("api/anime/detail/{animeId}")
    // suspend fun getAnimeDetail(@Path("animeId") id: String): DetailAnimeResponse
}