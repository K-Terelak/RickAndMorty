package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

val primaryLight = Color(0xFF8F4C38)
val onPrimaryLight = Color(0xFFFFFFFF)
val secondaryLight = Color(0xFF77574E)
val onSecondaryLight = Color(0xFFFFFFFF)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val backgroundLight = Color(0xFFFFF8F6)
val onBackgroundLight = Color(0xFF231917)
val surfaceLight = Color(0xFFFFF8F6)
val onSurfaceLight = Color(0xFF231917)
val surfaceVariantLight = Color(0xFFF5DED8)


val primaryDark = Color(0xFFFFB5A0)
val onPrimaryDark = Color(0xFF561F0F)
val secondaryDark = Color(0xFFE7BDB2)
val onSecondaryDark = Color(0xFF442A22)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val backgroundDark = Color(0xFF1A110F)
val onBackgroundDark = Color(0xFFF1DFDA)
val surfaceDark = Color(0xFF1A110F)
val onSurfaceDark = Color(0xFFF1DFDA)
val surfaceVariantDark = Color(0xFF53433F)


// Define light and dark color palettes
private val LightColors = Colors(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    primaryVariant = surfaceVariantLight,
    secondaryVariant = secondaryLight,
    background = backgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    error = errorLight,
    onBackground = onBackgroundLight,
    onError = onErrorLight,
    isLight = true
)

private val DarkColors = Colors(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    primaryVariant = surfaceVariantDark,
    secondaryVariant = secondaryDark,
    background = backgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    error = errorDark,
    onBackground = onBackgroundDark,
    onError = onErrorDark,
    isLight = false
)

// Define your typography
val AppTypography = Typography(
    defaultFontFamily = FontFamily.Default
)


@Composable
fun RAMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = AppTypography,
        content = content
    )
}
