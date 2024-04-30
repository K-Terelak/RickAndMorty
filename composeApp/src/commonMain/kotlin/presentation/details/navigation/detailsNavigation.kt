package presentation.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.details.DetailsRoute
import presentation.navigation.Screens


private const val CHARACTER_ID_ARG = "characterId"

fun NavController.navigateToDetails(characterId: Int) =
    navigate(Screens.DetailsScreen.route(characterId))

fun NavGraphBuilder.detailsScreen(
    onNavigateUp: () -> Unit,
) {
    composable(
        route = Screens.DetailsScreen.route + "/{$CHARACTER_ID_ARG}",
        arguments = listOf(navArgument(CHARACTER_ID_ARG) { type = NavType.IntType })
    ) { backStackEntry ->

        DetailsRoute(
            onNavigateUp = onNavigateUp,
            characterId = backStackEntry.arguments?.getInt(CHARACTER_ID_ARG) ?: 0
        )
    }
}

