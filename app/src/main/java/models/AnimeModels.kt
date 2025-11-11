package models
import com.google.gson.annotations.SerializedName

// Model untuk respons utama dari API baru
data class AnimeApiResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("result")
    val result: List<AnimeItem>,

    @SerializedName("creator")
    val creator: String
)

// Model untuk satu item anime
data class AnimeItem(
    @SerializedName("title")
    val title: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("poster")
    val poster: String, // URL gambar

    @SerializedName("current_episode")
    val currentEpisode: String,

    @SerializedName("release_day")
    val releaseDay: String,

    @SerializedName("newest_release_date")
    val newestReleaseDate: String,

    @SerializedName("otakudesu_url")
    val otakudesuUrl: String
)