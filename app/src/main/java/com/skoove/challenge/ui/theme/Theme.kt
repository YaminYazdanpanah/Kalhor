package com.skoove.challenge.ui.theme

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryVariantDark,
    secondary = secondaryDark,
    secondaryVariant = secondaryVariantDark,
    background = backgroundDark,
    surface = surfaceDark,
    error = errorDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    onBackground = onBackgroundDark,
    onSurface = onSurfaceDark,
    onError = onErrorDark,
)

enum class UiMode {
    Default,
    Dark;
}

val LocalUiMode = staticCompositionLocalOf<MutableState<UiMode>> {
    error("UiMode not provided")
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val isSystemDark = isSystemInDarkTheme()
    val uiMode = remember {
        mutableStateOf(if (isSystemDark) UiMode.Dark else UiMode.Default)
    }
    CompositionLocalProvider(
        LocalUiMode provides uiMode,
        LocalSpacing provides Spacing(),
        LocalTypography provides LocalMyTypography,
    ) {
        val colors = when (LocalUiMode.current.value) {
            UiMode.Default -> LightColorPalette
            UiMode.Dark -> DarkColorPalette
        }
        MaterialTheme(
            colors = animate(colors),
            shapes = shapes,
            content = content
        )
    }
}

@Composable
private fun animate(colors: Colors): Colors {
    val animSpec = remember {
        spring<Color>(stiffness = 500f)
    }

    @Composable
    fun animateColor(color: Color): Color =
        animateColorAsState(targetValue = color, animationSpec = animSpec).value

    return Colors(
        primary = animateColor(colors.primary),
        primaryVariant = animateColor(colors.primaryVariant),
        secondary = animateColor(colors.secondary),
        secondaryVariant = animateColor(colors.secondaryVariant),
        background = animateColor(colors.background),
        surface = animateColor(colors.surface),
        error = animateColor(colors.error),
        onPrimary = animateColor(colors.onPrimary),
        onSecondary = animateColor(colors.onSecondary),
        onBackground = animateColor(colors.onBackground),
        onSurface = animateColor(colors.onSurface),
        onError = animateColor(colors.onError),
        isLight = colors.isLight,
    )
}

