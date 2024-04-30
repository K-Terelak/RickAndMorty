package presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.remote.Resource
import domain.repository.RickMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.mapper.toCharacterUi

class DetailsViewModel(
    private val repository: RickMortyRepository,
) : ViewModel() {


    private val _characterDetailsUiState = MutableStateFlow(CharacterDetailsUiState())
    val characterDetailsUiState get() = _characterDetailsUiState.asStateFlow()

    fun loadDetails(characterId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repository.getCharacterDetails(characterId)
            if (response is Resource.Success) {
                _characterDetailsUiState.update { listState ->
                    listState.copy(response.data.toCharacterUi())
                }
            }
        }
    }
}
