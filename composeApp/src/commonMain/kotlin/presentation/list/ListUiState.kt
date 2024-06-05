package presentation.list

import presentation.model.CharacterUi

sealed class ListUiState {
    data class Loaded(
        val list: List<CharacterUi> = emptyList()
    ) : ListUiState()

    data object Loading : ListUiState()
    data object Error : ListUiState()
}
