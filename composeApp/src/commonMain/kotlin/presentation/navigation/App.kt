package presentation.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.details.navigation.detailsScreen
import presentation.details.navigation.navigateToDetails
import presentation.list.navigation.listScreen

@Composable
@Preview
fun App() {

    KoinContext {
        val navController = rememberNavController()

        MaterialTheme {
            NavHost(
                navController = navController,
                startDestination = Screens.ListScreen.route,
            ) {
                listScreen(onDetailsClick = navController::navigateToDetails)
                detailsScreen(onNavigateUp = navController::navigateUp)
            }
        }
    }
}
