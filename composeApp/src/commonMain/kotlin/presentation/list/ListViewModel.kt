package presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.remote.Resource
import domain.repository.RickMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.mapper.toCharacterUi

class ListViewModel(private val repository: RickMortyRepository) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState())
    val listUiState get() = _listUiState.asStateFlow()

    init {
        getCharacters()
    }

    fun sortByName(sortTypeOnView: SortTypeOnView) {
        viewModelScope.launch(Dispatchers.IO) {
            _listUiState.update {
                val sortedList = when (sortTypeOnView) {
                    SortTypeOnView.Asc -> it.list.sortedBy { item -> item.name }
                    SortTypeOnView.Desc -> it.list.sortedByDescending { item -> item.name }
                    SortTypeOnView.None -> it.list.sortedBy { item -> item.id }
                }
                it.copy(list = sortedList)
            }
        }
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repository.getCharacters()
            if (response is Resource.Success) {
                _listUiState.update { listState ->
                    listState.copy(response.data.map { it.toCharacterUi() })
                }
            }
        }
    }
}
