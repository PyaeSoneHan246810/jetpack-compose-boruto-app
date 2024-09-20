package com.example.borutoapp.presentation.splash.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.splash.component.AnimatedSplashLogo
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.Pink
import com.example.borutoapp.presentation.ui.theme.PinkLight

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashLogoVisible: Boolean
) {

    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (!isSystemInDarkTheme()) {
            SplashContentLightMode(
                splashLogoVisible = splashLogoVisible
            )
        } else {
            SplashContentDarkMode(
                splashLogoVisible = splashLogoVisible
            )
        }
    }
}

@Composable
fun SplashContentLightMode(
    modifier: Modifier = Modifier,
    splashLogoVisible: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(Pink, PinkLight))
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedSplashLogo(
            splashLogoVisible = splashLogoVisible
        )
    }
}

@Composable
fun SplashContentDarkMode(
    modifier: Modifier = Modifier,
    splashLogoVisible: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedSplashLogo(
            splashLogoVisible = splashLogoVisible
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Composable
private fun SplashContentLightModePrev() {
    MainAppTheme {
        SplashContentLightMode(
            splashLogoVisible = true
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun SplashContentDarkModePrev() {
    MainAppTheme {
        SplashContentDarkMode(
            splashLogoVisible = true
        )
    }
}