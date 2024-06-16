package presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.remote.Resource
import domain.repository.RickMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.mapper.toCharacterUi

class ListViewModel(private val repository: RickMortyRepository) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState())
    val listUiState get() = _listUiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()


    init {
        getCharacters("")
        observeSearchText()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }


    @OptIn(FlowPreview::class)
    private fun observeSearchText() {
        viewModelScope.launch {
            _searchText
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    getCharacters(query)
                }
        }
    }

    private fun getCharacters(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repository.getCharacters(query)
            if (response is Resource.Success) {
                _listUiState.update { listState ->
                    listState.copy(response.data.map { it.toCharacterUi() })
                }
            }
        }
    }
}
