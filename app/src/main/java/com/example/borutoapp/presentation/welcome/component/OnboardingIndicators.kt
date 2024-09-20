package com.example.borutoapp.presentation.welcome.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.borutoapp.presentation.ui.theme.ONBOARDING_INDICATOR_SIZE
import com.example.borutoapp.presentation.ui.theme.PADDING_EXTRA_SMALL

@Composable
fun OnboardingIndicators(
    modifier: Modifier = Modifier,
    onboardingPageSize: Int,
    selectedPageIndex: Int,
    selectedItemColor: Color = MaterialTheme.colorScheme.primary,
    unselectedItemColor: Color = MaterialTheme.colorScheme.outline
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PADDING_EXTRA_SMALL)
    ) {
        repeat(onboardingPageSize) { pageIndex ->
            Box(
                modifier = Modifier
                    .size(ONBOARDING_INDICATOR_SIZE)
                    .clip(CircleShape)
                    .background(if (pageIndex == selectedPageIndex) selectedItemColor else unselectedItemColor),
            )
        }
    }
}