package com.example.borutoapp.presentation.welcome.screen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.navigation.Screen
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.welcome.component.GetStartedButton
import com.example.borutoapp.presentation.welcome.component.OnboardingIndicators
import com.example.borutoapp.presentation.welcome.component.OnboardingPager
import com.example.borutoapp.presentation.welcome.event.WelcomeEvent

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: (route: String) -> Unit,
    onSaveAppEntryState: (event: WelcomeEvent) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            onboardingPages.size
        }
    )
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .statusBarsPadding(),
            ) {
                OnboardingPager(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = pagerState
                )
                OnboardingIndicators(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    onboardingPageSize = onboardingPages.size,
                    selectedPageIndex = pagerState.currentPage
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.18f)
                    .navigationBarsPadding(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(
                        visible = pagerState.currentPage == onboardingPages.size - 1,
                        enter = slideInVertically(
                            initialOffsetY = { it / 2 }
                        ) + fadeIn(),
                        exit = slideOutVertically(
                            targetOffsetY = { it / 2 }
                        ) + fadeOut()
                    ) {
                        GetStartedButton(
                            onGetStartedClicked = {
                                onSaveAppEntryState(WelcomeEvent.SaveAppEntryState)
                                onNavigateToHomeScreen(Screen.Home.route)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun WelcomeScreenPrev() {
    MainAppTheme {
        WelcomeScreen(
            onNavigateToHomeScreen = {},
            onSaveAppEntryState = {}
        )
    }
}