package data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("created")
    val created: String?,
    @SerialName("episode")
    val episode: List<String?>?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("location")
    val locationResponse: LocationResponse?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin")
    val originResponse: OriginResponse?,
    @SerialName("species")
    val species: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?
)
