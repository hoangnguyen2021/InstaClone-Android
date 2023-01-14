package myapp.hoang.core_ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val MainDarkColorScheme = darkColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = SkyBlue,
    onPrimaryContainer = White,
    secondary = Black,
    onSecondary = Gray100,
    secondaryContainer = Gray800,
    onSecondaryContainer = White,
    tertiary = Red,
    onTertiary = White,
    tertiaryContainer = Gray500,
    onTertiaryContainer = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    surfaceVariant = Gray900,
    onSurfaceVariant = Gray200,
    outline = Gray800
)

private val MainLightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = SkyBlue,
    onPrimaryContainer = White,
    secondary = White,
    onSecondary = Black,
    secondaryContainer = Light,
    onSecondaryContainer = Black,
    tertiary = Red,
    onTertiary = White,
    tertiaryContainer = Gray300,
    onTertiaryContainer = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = White,
    onSurfaceVariant = Gray300,
    outline = Gray800
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

