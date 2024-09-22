package com.example.borutoapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.domain.model.Hero

@Composable
fun HeroesContent(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    onHeroClick: (heroId: Int) -> Unit
) {
    val heroesLoadState = heroes.loadState
    val error = when {
        heroesLoadState.refresh is LoadState.Error -> heroesLoadState.refresh as LoadState.Error
        heroesLoadState.prepend is LoadState.Error -> heroesLoadState.prepend as LoadState.Error
        heroesLoadState.append is LoadState.Error -> heroesLoadState.append as LoadState.Error
        else -> null
    }
    when {
        heroesLoadState.refresh is LoadState.Loading -> {
            ShimmerItemsList()
        }
        error != null -> {

        }
        else -> {
            HeroItemsList(
                modifier = modifier,
                heroes = heroes,
                onHeroClick = onHeroClick
            )
        }
    }
}