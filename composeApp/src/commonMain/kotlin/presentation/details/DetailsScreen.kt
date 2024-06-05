package presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun DetailsRoute(
    onNavigateUp: () -> Unit,
    characterId: Int,
    viewModel: DetailsViewModel = koinInject()
) {

    val characterDetailsUiState: CharacterDetailsUiState by viewModel.characterDetailsUiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadDetails(characterId)
    }
    DetailsScreen(
        characterDetailsUiState = characterDetailsUiState,
        onNavigateUp = onNavigateUp
    )
}

@Composable
private fun DetailsScreen(
    modifier: Modifier = Modifier,
    characterDetailsUiState: CharacterDetailsUiState,
    onNavigateUp: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.surface,
            title = {
                Text(
                    text = "Character details",
                    color = MaterialTheme.colors.onBackground
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate up",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            text = characterDetailsUiState.list.name,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            text = characterDetailsUiState.list.gender,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            text = characterDetailsUiState.list.species,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            text = characterDetailsUiState.list.status,
            color = MaterialTheme.colors.onBackground
        )
    }
}
