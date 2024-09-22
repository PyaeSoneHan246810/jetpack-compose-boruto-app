package com.example.borutoapp.presentation.welcome.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.borutoapp.R
import com.example.borutoapp.presentation.ui.theme.ONBOARDING_IMAGE_SIZE
import com.example.borutoapp.presentation.ui.theme.PADDING_EXTRA_LARGE
import com.example.borutoapp.presentation.ui.theme.PADDING_MEDIUM
import com.example.borutoapp.presentation.welcome.screen.OnboardingPage
import com.example.borutoapp.presentation.welcome.screen.onboardingPages

@Composable
fun OnboardingPager(
    modifier: Modifier = Modifier,
    state: PagerState,
) {
    HorizontalPager(
        modifier = modifier,
        state = state
    ) { index ->
        OnboardingPage(
            onboardingPage = onboardingPages[index]
        )
    }
}

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    onboardingPage: OnboardingPage
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = PADDING_MEDIUM
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(ONBOARDING_IMAGE_SIZE),
            painter = painterResource(id = onboardingPage.image),
            contentDescription = stringResource(id = R.string.onboarding_image_content_desc),
            contentScale = ContentScale.Fit
        )
        Spacer(
            modifier = Modifier
                .height(PADDING_EXTRA_LARGE)
        )
        Text(
            text = onboardingPage.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(
            modifier = Modifier
                .height(PADDING_MEDIUM)
        )
        Text(
            text = onboardingPage.description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}