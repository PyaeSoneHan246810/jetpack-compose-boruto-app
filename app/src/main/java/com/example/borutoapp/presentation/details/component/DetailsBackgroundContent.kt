package com.example.borutoapp.presentation.details.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.borutoapp.R
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.PADDING_EXTRA_LARGE
import com.example.borutoapp.presentation.ui.theme.PADDING_MEDIUM
import com.example.borutoapp.util.Constants

@Composable
fun DetailsBackgroundContent(
    modifier: Modifier = Modifier,
    image: String,
    imageContentDesc: String,
    imageFraction: Float,
    closeIconColor: Color,
    onCloseIconButtonClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("${Constants.API_BASE_URL}$image")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .crossfade(true)
                .build(),
            contentDescription = imageContentDesc,
            contentScale = ContentScale.Crop
        )
        IconButton(
            modifier = Modifier
                .padding(
                    top = PADDING_EXTRA_LARGE,
                    end = PADDING_MEDIUM
                )
                .align(Alignment.TopEnd),
            onClick = onCloseIconButtonClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = closeIconColor,
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun DetailsBackgroundContentPrev() {
    MainAppTheme {
        DetailsBackgroundContent(
            image = "/images/sasuke.jpg",
            imageContentDesc = "Sasuke",
            imageFraction = 1f,
            closeIconColor = MaterialTheme.colorScheme.primary,
            onCloseIconButtonClick = {}
        )
    }
}