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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.ui.theme.ERROR_IMAGE_SIZE
import com.example.borutoapp.presentation.ui.theme.MainAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDisplay(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    @StringRes message: Int,
    @DrawableRes image: Int,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember {
        mutableStateOf(false)
    }
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
        pullToRefreshState = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            heroes.refresh()
            isRefreshing = false
        },
        alphaValue = alphaState.value,
        message = message,
        image = image
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    pullToRefreshState: PullToRefreshState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    alphaValue: Float,
    @StringRes message: Int,
    @DrawableRes image: Int
) {
    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize(),
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(ERROR_IMAGE_SIZE)
                    .alpha(alphaValue),
                painter = painterResource(id = image),
                contentDescription = stringResource(id = R.string.error_image_content_desc),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .alpha(alphaValue),
                text = stringResource(id = message),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ErrorContentPrev() {
    MainAppTheme {
        ErrorContent(
            pullToRefreshState = rememberPullToRefreshState(),
            isRefreshing = false,
            onRefresh = {},
            alphaValue = 1f,
            message = R.string.server_error_message,
            image = R.drawable.error_server
        )
    }
}