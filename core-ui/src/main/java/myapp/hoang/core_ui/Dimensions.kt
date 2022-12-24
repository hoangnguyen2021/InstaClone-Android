package myapp.hoang.core_ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val zero: Dp = 0.dp,
    val twoExtraSmall: Dp = 2.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val mediumSmall: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val mediumLarge: Dp = 20.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 28.dp,
    val twoExtraLarge: Dp = 32.dp,
    val threeExtraLarge: Dp = 36.dp,
    val fourExtraLarge: Dp = 40.dp,
    val fiveExtraLarge: Dp = 48.dp,
    val sixExtraLarge: Dp = 56.dp,
    val sevenExtraLarge: Dp = 64.dp,
    val eightExtraLarge: Dp = 72.dp,
    val nineExtraLarge: Dp = 80.dp,
    val tenExtraLarge: Dp = 88.dp,
    val elevenExtraLarge: Dp = 96.dp,
    val twelveExtraLarge: Dp = 104.dp,
    val thirteenExtraLarge: Dp = 112.dp,
    val fourteenExtraLarge: Dp = 120.dp,
)

val LocalDimension = compositionLocalOf { Dimensions() }