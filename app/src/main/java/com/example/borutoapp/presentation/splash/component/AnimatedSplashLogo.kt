package com.example.borutoapp.presentation.splash.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.borutoapp.R
import com.example.borutoapp.presentation.ui.theme.SPLASH_SCREEN_LOGO_SIZE

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