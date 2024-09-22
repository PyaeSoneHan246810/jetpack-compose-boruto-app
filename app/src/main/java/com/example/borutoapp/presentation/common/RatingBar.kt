package com.example.borutoapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.RATING_STAR_COLOR

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    count: Int = 5,
    color: Color = RATING_STAR_COLOR
) {
    val filledStar = Icons.Rounded.Star
    val halfStar = Icons.AutoMirrored.Rounded.StarHalf
    val outlinedStar = Icons.Rounded.StarOutline
    var isHalfStar = (rating % 1) != 0.0
    Row(
        modifier = modifier
    ) {
        for (index in 1..count) {
            Icon(
                imageVector = if (index <= rating) {
                    filledStar
                } else {
                    if (isHalfStar) {
                        isHalfStar = false
                        halfStar
                    } else {
                        outlinedStar
                    }
                },
                contentDescription = null,
                tint = color
            )
       }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun RatingBarPrev() {
    MainAppTheme {
        RatingBar(
            rating = 1.0
        )
    }
}