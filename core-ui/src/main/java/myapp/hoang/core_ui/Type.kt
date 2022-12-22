package myapp.hoang.core_ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val MainTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
)

val proximaNovaFamily = FontFamily(
    Font(R.font.proximanova_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.proximanova_medium, FontWeight.Medium, FontStyle.Normal),
)

val arabotoFamily = FontFamily(
    Font(R.font.araboto_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.araboto_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.araboto_medium, FontWeight.Medium, FontStyle.Normal),
)

val shoikaFontFamily = FontFamily(
    Font(R.font.shoika_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.shoika_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.shoika_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.shoika_regular_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.shoika_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.shoika_light_italic, FontWeight.Light, FontStyle.Italic),
)

val OnBoardingTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = arabotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = shoikaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    )
)