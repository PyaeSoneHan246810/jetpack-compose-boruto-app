package com.example.borutoapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.ui.theme.PADDING_LARGE

@Composable
fun HeroItemsList(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    onHeroClick: (heroId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(PADDING_LARGE),
        verticalArrangement = Arrangement.spacedBy(PADDING_LARGE)
    ) {
        items(heroes.itemCount) { index ->
            val hero = heroes[index]
            hero?.let {
                HeroItem(
                    hero = it,
                    onHeroItemClick = onHeroClick
                )
            }
        }
    }
}