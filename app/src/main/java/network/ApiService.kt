package network

import retrofit2.http.GET
import retrofit2.http.Path
import models.AnimeApiResponse
import retrofit2.http.Query

interface ApiService {

    // Endpoint: https://api.ammaricano.my.id/api/otakudesu?type=ongoing&page=1
    @GET("api/otakudesu")
    suspend fun getOngoingAnime(
        @Query("type") type: String = "ongoing",
        @Query("page") page: Int = 1
    ): AnimeApiResponse

    // Jika nanti ada endpoint untuk complete anime
    @GET("api/otakudesu")
    suspend fun getCompleteAnime(
        @Query("type") type: String = "complete",
        @Query("page") page: Int = 1
    ): AnimeApiResponse
}