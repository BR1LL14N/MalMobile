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

data class AnimeDetailResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("result")
    val result: AnimeDetailResult
)

// Model untuk isi "result" dari detail
data class AnimeDetailResult(
    @SerializedName("poster")
    val poster: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("japanese")
    val japanese: String,

    @SerializedName("score")
    val score: String,

    @SerializedName("producer")
    val producer: String,

    @SerializedName("tipe")
    val tipe: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("total_episode")
    val totalEpisode: String,

    @SerializedName("duration")
    val duration: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("studio")
    val studio: String,

    @SerializedName("genre")
    val genre: String,

    @SerializedName("synopsis")
    val synopsis: String,

    @SerializedName("episodes")
    val episodes: List<EpisodeItem>
)

// Model untuk satu item episode
data class EpisodeItem(
    @SerializedName("episode")
    val episode: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("otakudesu_url")
    val otakudesuUrl: String
)


data class EpisodeDetailResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("result")
    val result: EpisodeDetailResult
)

data class EpisodeDetailResult(
    @SerializedName("title")
    val title: String,

    @SerializedName("has_next_episode")
    val hasNextEpisode: Boolean,

    @SerializedName("next_episode")
    val nextEpisode: NextEpisodeInfo?,

    @SerializedName("has_previous_episode")
    val hasPreviousEpisode: Boolean,

    @SerializedName("previous_episode")
    val previousEpisode: NextEpisodeInfo?,

    @SerializedName("stream_url")
    val streamUrl: String,

    @SerializedName("mirror")
    val mirror: MirrorLinks,

    @SerializedName("download")
    val download: DownloadLinks,

    val finalUrl: String? // tambahkan ini
)

data class NextEpisodeInfo(
    @SerializedName("slug")
    val slug: String,

    @SerializedName("otakudesu_url")
    val otakudesuUrl: String
)

data class MirrorLinks(
    @SerializedName("m360p")
    val m360p: List<MirrorItem>,

    @SerializedName("m480p")
    val m480p: List<MirrorItem>,

    @SerializedName("m720p")
    val m720p: List<MirrorItem>
)

data class MirrorItem(
    @SerializedName("nama")
    val nama: String,

    @SerializedName("content")
    val content: String
)

data class DownloadLinks(
    @SerializedName("dmp4360p")
    val dmp4360p: List<DownloadItem>,
    @SerializedName("dmp4480p")
    val dmp4480p: List<DownloadItem>,
    @SerializedName("dmp4720p")
    val dmp4720p: List<DownloadItem>,
    @SerializedName("dmkv480p")
    val dmkv480p: List<DownloadItem>,
    @SerializedName("dmkv720p")
    val dmkv720p: List<DownloadItem>,
    @SerializedName("dmkv1080p")
    val dmkv1080p: List<DownloadItem>
)

data class DownloadItem(
    @SerializedName("nama")
    val nama: String,

    @SerializedName("href")
    val href: String
)

data class StreamLinkResponse(
    @SerializedName("video")
    val video: String
    // Tambahkan field lain jika ada, tapi sepertinya kita hanya butuh 'video'
)