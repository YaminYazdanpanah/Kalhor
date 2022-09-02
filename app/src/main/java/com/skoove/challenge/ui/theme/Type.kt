package com.skoove.challenge.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.skoove.challenge.R


private val AppFontFamily = FontFamily(
    Font(R.font.font_regular),
    Font(R.font.font_light, FontWeight.Light),
    Font(R.font.font_bold, FontWeight.Bold),
)

@Immutable
data class MyCustomTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button1: TextStyle,
    val button2: TextStyle,
    val button3: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle,
    val overline2: TextStyle,
)

val LocalMyTypography = staticCompositionLocalOf {
    MyCustomTypography(
        h1 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(300),
            fontSize = 96.sp,
            lineHeight = 134.sp,
            fontFeatureSettings = "'liga' off"
        ),
        h2 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(300),
            fontSize = 60.sp,
            lineHeight = 84.sp,
            fontFeatureSettings = "'liga' off"
        ),
        h3 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(400),
            fontSize = 48.sp,
            lineHeight = 67.sp,
            fontFeatureSettings = "'liga' off"
        ),
        h4 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 30.sp,
            lineHeight = 42.sp,
            fontFeatureSettings = "'liga' off"
        ),
        h5 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 24.sp,
            lineHeight = 24.sp,
            fontFeatureSettings = "'liga' off"
        ),
        h6 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 20.sp,
            lineHeight = 32.sp,
            fontFeatureSettings = "'liga' off"
        ),
        subtitle1 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFeatureSettings = "'liga' off"
        ),
        subtitle2 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontFeatureSettings = "'liga' off"
        ),
        body1 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(400),
            fontSize = 16.sp,
            lineHeight = 28.sp,
            fontFeatureSettings = "'liga' off"
        ),
        body2 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            lineHeight = 24.sp,
            fontFeatureSettings = "'liga' off"
        ),
        button1 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFeatureSettings = "'liga' off"
        ),
        button2 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontFeatureSettings = "'liga' off"
        ),
        button3 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontFeatureSettings = "'liga' off"
        ),
        caption = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontFeatureSettings = "'liga' off"
        ),
        overline = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(700),
            fontSize = 10.sp,
            lineHeight = 16.sp,
            fontFeatureSettings = "'liga' off"
        ),
        overline2 = TextStyle(
            fontFamily = AppFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(400),
            fontSize = 10.sp,
            lineHeight = 16.sp,
            fontFeatureSettings = "'liga' off"
        ),
    )
}
internal val LocalTypography = staticCompositionLocalOf { LocalMyTypography }

val MaterialTheme.appTypography: MyCustomTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalMyTypography.current
