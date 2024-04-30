package presentation.navigation

sealed class Screens(val route: String) {
    data object ListScreen : Screens("list_screen")
    data object DetailsScreen : Screens("details_screen") {
        fun route(characterId: Int): String {
            return DetailsScreen.route + "/$characterId"
        }
    }
}
