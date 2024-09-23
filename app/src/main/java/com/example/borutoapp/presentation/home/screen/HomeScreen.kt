package com.example.borutoapp.presentation.home.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.common.HeroesContent
import com.example.borutoapp.presentation.home.component.HomeTopAppBar
import com.example.borutoapp.presentation.navigation.Screen
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    heroes: Flow<PagingData<Hero>>?,
    onNavigateToSearchScreen: (route: String) -> Unit,
    onNavigateToDetailsScreen: (route: String) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            HomeTopAppBar(
                onSearchClick = {
                    onNavigateToSearchScreen(Screen.Search.route)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding()
                )
                .navigationBarsPadding()
        ) {
            HeroesContent(
                heroesFlow = heroes,
                onHeroClick = { heroId ->
                    onNavigateToDetailsScreen(Screen.Details.passHeroId(heroId))
                }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun HomeScreenPrev() {
    MainAppTheme {
        HomeScreen(
            heroes = null,
            onNavigateToSearchScreen = {},
            onNavigateToDetailsScreen = {}
        )
    }
}