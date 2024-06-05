package presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.remote.Resource
import domain.repository.RickMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.mapper.toCharacterUi

class ListViewModel(private val repository: RickMortyRepository) : ViewModel() {

    private val _listUiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val listUiState get() = _listUiState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.Main) {
            _listUiState.value = ListUiState.Loading
            val response = repository.getCharacters()
            if (response is Resource.Success) {
                _listUiState.value =
                    ListUiState.Loaded(response.data.map { it.toCharacterUi() })
            } else {
                _listUiState.value = ListUiState.Error
            }
        }
    }
}
