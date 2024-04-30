package presentation.list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.list.ListRoute
import presentation.navigation.Screens


fun NavGraphBuilder.listScreen(
    onDetailsClick: (id: Int) -> Unit,
) {
    composable(route = Screens.ListScreen.route) {
        ListRoute(onDetailsClick = onDetailsClick)
    }
}
