package presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun ListRoute(
    onDetailsClick: (id: Int) -> Unit,
    viewModel: ListViewModel = koinInject()
) {
    val listUiState: ListUiState by viewModel.listUiState.collectAsState()

    ListScreen(
        onDetailsClick = onDetailsClick,
        listUiState = listUiState
    )
}

@Composable
private fun ListScreen(
    modifier: Modifier = Modifier,
    listUiState: ListUiState,
    onDetailsClick: (id: Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listUiState.list) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailsClick(it.id) },
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
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
