package com.example.borutoapp.presentation.splash.screen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.presentation.navigation.Screen
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.Pink
import com.example.borutoapp.presentation.ui.theme.PinkLight
import com.example.borutoapp.presentation.ui.theme.SPLASH_SCREEN_LOGO_SIZE
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToWelcomeScreen: (route: String) -> Unit
) {
    var splashLogoVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        splashLogoVisible = true
        delay(4000)
        onNavigateToWelcomeScreen(Screen.Welcome.route)
    }
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
                color = MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedSplashLogo(
            splashLogoVisible = splashLogoVisible
        )
    }
}

@Composable
fun AnimatedSplashLogo(
    splashLogoVisible: Boolean,
) {
    AnimatedVisibility(
        visible = splashLogoVisible,
        enter = scaleIn(),
    ) {
        Image(
            modifier = Modifier
                .size(SPLASH_SCREEN_LOGO_SIZE),
            painter = painterResource(id = R.drawable.splash_screen_logo),
            contentDescription = stringResource(id = R.string.splash_screen_logo)
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