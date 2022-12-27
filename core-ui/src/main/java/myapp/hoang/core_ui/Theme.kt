package myapp.hoang.core_ui

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val MainDarkColorScheme = darkColorScheme(
    primary = SkyBlue,
    onPrimary = White,
    primaryContainer = Color.Blue,
    onPrimaryContainer = White,
    secondary = Color.DarkGray,
    onSecondary = White,
    tertiary = Red,
    onTertiary = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
)

private val MainLightColorScheme = lightColorScheme(
    primary = SkyBlue,
    onPrimary = White,
    secondary = LightGray,
    onSecondary = Black,
    tertiary = Red,
    onTertiary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

@Composable
fun InstaCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        darkTheme -> MainDarkColorScheme
        else -> MainLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
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
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = OnboardingColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = OnBoardingTypography,
        content = content
    )
}

