package models
import com.google.gson.annotations.SerializedName

// Model untuk respons utama (untuk /complete dan /ongoing)
data class AnimeApiResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: AnimeListData,

    @SerializedName("meta")
    val meta: Meta
)

// Model untuk bagian "data"
data class AnimeListData(
    @SerializedName("anime_list")
    val animeList: List<AnimeItem>
)

// Model untuk satu item anime
// Kita gabungkan "complete" dan "ongoing" di sini
// 'score' dan 'day_updated' dibuat nullable (?) karena tidak ada di kedua respons
data class AnimeItem(
    @SerializedName("title")
    val title: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("thumb")
    val thumb: String, // URL gambar

    @SerializedName("episode")
    val episode: String,

    @SerializedName("uploaded_on")
    val uploadedOn: String,

    @SerializedName("link")
    val link: String, // URL detail

    // Hanya untuk 'complete'
    @SerializedName("score")
    val score: Double?,

    // Hanya untuk 'ongoing'
    @SerializedName("day_updated")
    val dayUpdated: String?
)

// Model untuk bagian "meta" (paginasi)
data class Meta(
    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("total_items")
    val totalItems: Int,

    @SerializedName("has_next_page")
    val hasNextPage: Boolean,

    @SerializedName("next_page")
    val nextPage: Int?
)

// Model terpisah untuk endpoint /api/ (Welcome)
data class WelcomeResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("createdBy")
    val createdBy: String
)
