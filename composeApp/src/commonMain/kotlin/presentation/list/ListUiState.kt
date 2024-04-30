package presentation.list

import presentation.model.CharacterUi

data class ListUiState(
    val list: List<CharacterUi> = emptyList()
)
