package com.example.borutoapp.presentation.search.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.common.HeroesContent
import com.example.borutoapp.presentation.navigation.Screen
import com.example.borutoapp.presentation.search.component.TopSearchBar
import com.example.borutoapp.presentation.search.event.SearchEvent
import com.example.borutoapp.presentation.search.state.SearchState
import com.example.borutoapp.presentation.ui.theme.MainAppTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchState: SearchState,
    onSearchQueryUpdated: (event: SearchEvent) -> Unit,
    onSearchQueryCleared: (event: SearchEvent) -> Unit,
    onSearchHeroes: (event: SearchEvent) -> Unit,
    onNavigateToDetailsScreen: (route: String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Spacer(
                    modifier = Modifier
                        .statusBarsPadding()
                )
                TopSearchBar(
                    modifier = Modifier,
                    text = searchState.searchQuery,
                    onTextChanged = { newText ->
                        onSearchQueryUpdated(SearchEvent.UpdateSearchQuery(newSearchQuery = newText))
                    },
                    onClearClicked = {
                        onSearchQueryCleared(SearchEvent.UpdateSearchQuery(newSearchQuery = ""))
                    },
                    onSearchClicked = {
                        onSearchHeroes(SearchEvent.SearchHeroes)
                    },
                )
            }
            HeroesContent(
                heroesFlow = searchState.heroes,
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
private fun SearchScreenPrev() {
    MainAppTheme {
        SearchScreen(
            searchState = SearchState(),
            onSearchQueryUpdated = {},
            onSearchQueryCleared = {},
            onSearchHeroes = {},
            onNavigateToDetailsScreen = {}
        )
    }
}