package com.example.borutoapp.presentation.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.common.HeroesContent
import com.example.borutoapp.presentation.home.component.HomeTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    onNavigateToDetailsScreen: (heroId: Int) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            HomeTopAppBar(
                onSearchClick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding()
                )
                .navigationBarsPadding()
        ) {
            HeroesContent(
                heroes = heroes,
                onHeroClick = onNavigateToDetailsScreen
            )
        }
    }
}