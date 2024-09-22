package com.example.borutoapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.domain.model.Hero

@Composable
fun HeroesContent(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    onHeroClick: (heroId: Int) -> Unit
) {
    HeroItemsList(
        modifier = modifier,
        heroes = heroes,
        onHeroClick = onHeroClick
    )
}