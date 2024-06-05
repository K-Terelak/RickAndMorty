package presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import presentation.model.CharacterUi
import presentation.util.shimmerLoadingAnimation

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
        modifier = modifier.fillMaxSize(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        when (listUiState) {
            ListUiState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Something went wrong :(",
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
                        )
                    }
                }
            }

            is ListUiState.Loaded -> {
                items(listUiState.list) {
                    CharacterItem(
                        characterUi = it,
                        onDetailsClick = onDetailsClick
                    )
                }
            }

            ListUiState.Loading -> {
                items(12) {
                    CharacterItemShimmer()
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    characterUi: CharacterUi,
    onDetailsClick: (id: Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onDetailsClick(characterUi.id) },
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
                text = characterUi.name
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                style = MaterialTheme.typography.body2,
                text = characterUi.species
            )
        }
    }
}

@Composable
fun CharacterItemShimmer(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(MaterialTheme.typography.body1.lineHeight.value.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerLoadingAnimation(true),
            )

            Spacer(modifier = Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.typography.body2.lineHeight.value.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerLoadingAnimation(true),
            )
        }
    }
}
