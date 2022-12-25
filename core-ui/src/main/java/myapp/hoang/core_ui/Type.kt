package myapp.hoang.core_ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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
    titleLarge = TextStyle(
        fontFamily = arabotoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.6.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.4.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = arabotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.4.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = shoikaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    )
)