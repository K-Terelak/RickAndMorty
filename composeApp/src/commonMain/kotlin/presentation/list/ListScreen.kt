package presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
        listUiState = listUiState,
        onSortByNameClick = viewModel::sortByName
    )
}

@Composable
private fun ListScreen(
    modifier: Modifier = Modifier,
    listUiState: ListUiState,
    onDetailsClick: (id: Int) -> Unit,
    onSortByNameClick: (SortTypeOnView) -> Unit,
) {
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxSize()) {

        SortRow(onSortByNameClick = onSortByNameClick)

        LazyColumn(
            modifier = modifier.fillMaxSize(),
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
}

@Composable
fun SortRow(
    modifier: Modifier = Modifier,
    onSortByNameClick: (SortTypeOnView) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SortItem(
            modifier = Modifier.weight(1f),
            text = "Sort by: Name",
            initialSortType = SortTypeOnView.None,
            onSortChange = onSortByNameClick
        )
    }
}


@Composable
fun SortItem(
    modifier: Modifier = Modifier,
    text: String,
    initialSortType: SortTypeOnView,
    onSortChange: (SortTypeOnView) -> Unit
) {
    var sortType by remember { mutableStateOf(initialSortType) }
    val interactionSource = remember { MutableInteractionSource() }
    val rotation by animateFloatAsState(
        targetValue = if (sortType is SortTypeOnView.Asc) 0f else 180f
    )

    Row(
        modifier = modifier.padding(8.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                sortType = when (sortType) {
                    is SortTypeOnView.None -> SortTypeOnView.Asc
                    is SortTypeOnView.Asc -> SortTypeOnView.Desc
                    is SortTypeOnView.Desc -> SortTypeOnView.None
                }
                onSortChange(sortType)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6
        )
        AnimatedVisibility(sortType !is SortTypeOnView.None) {
            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .rotate(rotation),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }
    }
}

sealed interface SortTypeOnView {
    data object Asc : SortTypeOnView
    data object Desc : SortTypeOnView
    data object None : SortTypeOnView
}
