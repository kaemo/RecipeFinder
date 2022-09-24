package pl.kaemo.recipefinder.ui.favouritesActivity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = DmDefaultAndroidText,
    secondary = Teal200,
    background = DmBackgroundColor,
    surface = DmCardColor
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = DefaultAndroidText,
    secondary = Teal200,
    background = BackgroundColor,
    surface = CardColor

    /*
    secondaryVariant: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color
    */
)

@Composable
fun RecipeFinderTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}