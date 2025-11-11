package network

import retrofit2.http.GET
import retrofit2.http.Path
import models.AnimeApiResponse
import retrofit2.http.Query
import models.AnimeDetailResponse
import models.EpisodeDetailResponse
import models.StreamLinkResponse
import retrofit2.http.Url

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

    @GET("api/otakudesu/detail/{slug}")
    suspend fun getAnimeDetail(
        @Path("slug") slug: String
    ): AnimeDetailResponse

    @GET("api/otakudesu/episode/{slug}")
    suspend fun getEpisodeDetail(
        @Path("slug") slug: String
    ): EpisodeDetailResponse

    /**
     * Mengambil link video final dari URL stream (yang sudah ditambah &mode=json)
     * @param url URL lengkap yang diberikan oleh API (mis: "https://desustream.info/...")
     */
    @GET
    suspend fun getStreamLink(@Url url: String): StreamLinkResponse
}