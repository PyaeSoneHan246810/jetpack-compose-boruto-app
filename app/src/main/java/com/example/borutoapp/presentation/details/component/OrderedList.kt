package com.example.borutoapp.presentation.details.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.PADDING_MEDIUM

@Composable
fun OrderedList(
    modifier: Modifier = Modifier,
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
        Spacer(modifier = Modifier.height(PADDING_MEDIUM))
        items.forEachIndexed { index, text ->
            Text(
                modifier = Modifier
                    .alpha(0.8f),
                text = "${index + 1}. $text",
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun OrderedListPrev() {
    MainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            OrderedList(
                title = "Family",
                items = listOf("Fugaku", "Mikoto", "Itachi", "Sarada", "Sakura"),
                textColor = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}