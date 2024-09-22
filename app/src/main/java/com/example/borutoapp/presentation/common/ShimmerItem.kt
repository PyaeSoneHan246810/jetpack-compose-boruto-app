package com.example.borutoapp.presentation.common

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.ui.theme.HERO_ITEM_HEIGHT
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.OFF_BLACK
import com.example.borutoapp.presentation.ui.theme.OFF_WHITE
import com.example.borutoapp.presentation.ui.theme.PADDING_LARGE
import com.example.borutoapp.presentation.ui.theme.PADDING_MEDIUM
import com.example.borutoapp.presentation.ui.theme.PADDING_SMALL
import com.example.borutoapp.presentation.ui.theme.SHIMMER_PLACEHOLDER_SIZE_MEDIUM
import com.example.borutoapp.presentation.ui.theme.SHIMMER_PLACEHOLDER_SIZE_SMALL
import com.example.borutoapp.presentation.ui.theme.Shape

@Composable
fun ShimmerItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT)
            .clip(Shape.large)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = if (isSystemInDarkTheme()) OFF_BLACK else OFF_WHITE
        ){}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .align(Alignment.BottomCenter)
                .padding(PADDING_LARGE),
        ) {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(SHIMMER_PLACEHOLDER_SIZE_MEDIUM)
            )
            Spacer(modifier = Modifier.height(PADDING_MEDIUM))
            ShimmerPlaceholder()
            Spacer(modifier = Modifier.height(PADDING_SMALL))
            ShimmerPlaceholder()
            Spacer(modifier = Modifier.height(PADDING_SMALL))
            ShimmerPlaceholder()
            Spacer(modifier = Modifier.height(PADDING_MEDIUM))
            Row {
                repeat(5) {
                    ShimmerPlaceholder(
                        modifier = Modifier
                            .size(SHIMMER_PLACEHOLDER_SIZE_MEDIUM)
                    )
                    Spacer(modifier = Modifier.width(PADDING_SMALL))
                }
            }
        }
    }
}

@Composable
fun ShimmerPlaceholder(
    modifier: Modifier = Modifier,
    color: Color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SHIMMER_PLACEHOLDER_SIZE_SMALL)
            .clip(Shape.extraSmall)
            .shimmerEffect(color)
    )
}

fun Modifier.shimmerEffect(color: Color) = composed {
    val infiniteTransition = rememberInfiniteTransition(
        label = ""
    )
    val alpha = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    background(
        color = color.copy(
            alpha = alpha.value
        )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ShimmerItemPrev() {
    MainAppTheme {
        ShimmerItem()
    }
}