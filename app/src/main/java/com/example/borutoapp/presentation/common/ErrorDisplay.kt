package com.example.borutoapp.presentation.common

import android.content.res.Configuration
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.presentation.ui.theme.ERROR_IMAGE_SIZE
import com.example.borutoapp.presentation.ui.theme.MainAppTheme

@Composable
fun ErrorDisplay(
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int,
    @DrawableRes errorImage: Int
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
    ErrorContent(
        modifier = modifier,
        alphaValue = alphaState.value,
        errorMessage = errorMessage,
        errorImage = errorImage
    )
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    alphaValue: Float,
    @StringRes errorMessage: Int,
    @DrawableRes errorImage: Int
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(ERROR_IMAGE_SIZE)
                    .alpha(alphaValue),
                painter = painterResource(id = errorImage),
                contentDescription = stringResource(id = R.string.error_image_content_desc),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .alpha(alphaValue),
                text = stringResource(id = errorMessage),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ErrorContentPrev() {
    MainAppTheme {
        ErrorContent(
            alphaValue = 1f,
            errorMessage = R.string.server_error_message,
            errorImage = R.drawable.error_server
        )
    }
}