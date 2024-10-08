package com.example.borutoapp.presentation.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.borutoapp.R
import com.example.borutoapp.presentation.ui.theme.ERROR_IMAGE_SIZE

@Composable
fun EmptyDisplay(
    modifier: Modifier = Modifier,
    @StringRes message: Int,
    @DrawableRes image: Int
) {
    var animationStarted by rememberSaveable {
        mutableStateOf(false)
    }
    val alphaState = animateFloatAsState(
        targetValue = if (animationStarted) 1f else 0f,
        label = "",
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        animationStarted = true
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(ERROR_IMAGE_SIZE)
                .alpha(alphaState.value),
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.error_image_content_desc),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .alpha(alphaState.value),
            text = stringResource(id = message),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}