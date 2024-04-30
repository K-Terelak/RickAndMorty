import androidx.compose.ui.window.ComposeUIViewController
import presentation.navigation.App
import di.dataSourceModule
import di.networkModule
import di.repositoryModule
import di.viewModelModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }


fun initKoin() {
    startKoin {
        modules(networkModule, repositoryModule, dataSourceModule, viewModelModule)
    }
}
