package com.example.borutoapp.presentation.welcome.component

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GetStartedButton(
    modifier: Modifier = Modifier,
    onGetStartedClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onGetStartedClicked
    ) {
        Text(
            text = "Get Started",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}