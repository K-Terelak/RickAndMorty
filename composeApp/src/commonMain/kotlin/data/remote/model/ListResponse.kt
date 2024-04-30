package data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponse(
    @SerialName("info")
    val infoResponse: InfoResponse?,
    @SerialName("results")
    val characterResponses: List<CharacterResponse?>?
)
