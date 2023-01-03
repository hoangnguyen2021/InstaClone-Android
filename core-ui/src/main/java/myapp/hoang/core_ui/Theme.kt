package myapp.hoang.core_ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val MainDarkColorScheme = darkColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = SkyBlue,
    onPrimaryContainer = White,
    secondary = Color.DarkGray,
    onSecondary = White,
    tertiary = Red,
    onTertiary = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    outline = Darkness
)

private val MainLightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = SkyBlue,
    onPrimaryContainer = White,
    secondary = LightGray,
    onSecondary = Black,
    tertiary = Red,
    onTertiary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    outline = Darkness
)

@Composable
fun InstaCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> MainDarkColorScheme
        else -> MainLightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = colorScheme.primary,
            darkIcons = !darkTheme
        )

        onDispose {}
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MainTypography,
        content = content
    )
}

private val OnboardingColorScheme = lightColorScheme(
    primary = MidnightGreen,
    onPrimary = WeldonBlue,
    primaryContainer = MidnightGreen,
    onPrimaryContainer = White,
    secondary = MidnightGreen,
    onSecondary = GrayBlue,
    tertiaryContainer = Blue,
    onTertiaryContainer = White,
    background = MidnightGreen,
    onBackground = WeldonBlue,
    surface = Black,
    onSurface = White,
    outline = LightBlue,
    error = ErrorRed,
    onError = ErrorBlackRed
)

@Composable
fun OnBoardingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = OnboardingColorScheme

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = colorScheme.primary,
            darkIcons = !darkTheme
        )

        onDispose {}
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = OnBoardingTypography,
        content = content
    )
}

