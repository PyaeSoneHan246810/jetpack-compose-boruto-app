package com.example.borutoapp.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Pink,
    secondary = Pink,
    onPrimary = OFF_WHITE,
    onSecondary = OFF_WHITE,
    surface = SURFACE_DARK,
    onSurface = OFF_WHITE
)

private val LightColorScheme = lightColorScheme(
    primary = PinkLight,
    secondary = PinkLight,
    onPrimary = OFF_WHITE,
    onSecondary = OFF_WHITE,
    surface = SURFACE_LIGHT,
    onSurface = OFF_BLACK
)

@Composable
fun MainAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}