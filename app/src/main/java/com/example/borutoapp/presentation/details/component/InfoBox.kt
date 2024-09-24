package com.example.borutoapp.presentation.details.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.PADDING_SMALL

@Composable
fun InfoBox(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(INFO_ICON_SIZE),
            imageVector = icon, 
            contentDescription = null,
            tint = iconColor
        )
        Spacer(modifier = Modifier.width(PADDING_SMALL))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = bigText,
                color = textColor,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
            Text(
                modifier = Modifier
                    .alpha(0.8f),
                text = smallText,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun InfoBoxPrev() {
    MainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            InfoBox(
                icon = Icons.Filled.Bolt,
                iconColor = MaterialTheme.colorScheme.primary,
                bigText = "92",
                smallText = "Power",
                textColor = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}