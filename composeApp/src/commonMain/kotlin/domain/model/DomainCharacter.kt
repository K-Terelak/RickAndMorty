package domain.model

data class DomainCharacter(
    val id: Int,
    val created: String?,
    val episode: List<String?>?,
    val gender: String?,
    val image: String?,
    val name: String?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)
