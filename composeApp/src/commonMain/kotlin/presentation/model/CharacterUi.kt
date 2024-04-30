package presentation.model

data class CharacterUi(
    val id: Int = 0,
    val created: String = "",
    val episode: List<String?> = emptyList(),
    val gender: String = "",
    val image: String = "",
    val name: String = "",
    val species: String = "",
    val status: String = "",
    val type: String = "",
    val url: String = ""
)
