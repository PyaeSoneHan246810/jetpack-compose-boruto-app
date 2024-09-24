package com.example.borutoapp.presentation.details.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.borutoapp.R
import com.example.borutoapp.presentation.ui.theme.LOADING_INDICATOR_SIZE
import com.example.borutoapp.presentation.ui.theme.MainAppTheme

@Composable
fun DetailsLoadingIndicator(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val composition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_anim))
            LottieAnimation(
                modifier = Modifier
                    .size(LOADING_INDICATOR_SIZE),
                composition = composition.value,
                restartOnPlay = true,
                speed = 2f,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun DetailsLoadingIndicatorPrev() {
    MainAppTheme {
        DetailsLoadingIndicator()
    }
}