package presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun ListRoute(
    onDetailsClick: (id: Int) -> Unit,
    viewModel: ListViewModel = koinInject()
) {
    val listUiState: ListUiState by viewModel.listUiState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    ListScreen(
        onDetailsClick = onDetailsClick,
        listUiState = listUiState,
        searchText = searchText,
        onSearchChanged = viewModel::onSearchTextChange
    )
}

@Composable
private fun ListScreen(
    modifier: Modifier = Modifier,
    listUiState: ListUiState,
    searchText: String,
    onSearchChanged: (String) -> Unit,
    onDetailsClick: (id: Int) -> Unit,
) {
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxSize()) {

        SearchBar(
            modifier = Modifier.align(CenterHorizontally),
            searchText = searchText,
            onSearchChanged = onSearchChanged
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listUiState.list) {
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onDetailsClick(it.id) },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.body1,
                            text = it.name
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                            style = MaterialTheme.typography.body2,
                            text = it.species
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier, searchText: String, onSearchChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(0.8f).padding(4.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null
            )
        },
        maxLines = 1,
        singleLine = true,
        shape = CircleShape,
        value = searchText,
        onValueChange = onSearchChanged
    )
}

