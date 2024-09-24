package com.example.borutoapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun HeroesContent(
    modifier: Modifier = Modifier,
    heroesFlow:  Flow<PagingData<Hero>>?,
    onHeroClick: (heroId: Int) -> Unit
) {
    if (heroesFlow != null) {
        val heroes = heroesFlow.collectAsLazyPagingItems()
        val heroesLoadState = heroes.loadState
        val error = when {
            heroesLoadState.refresh is LoadState.Error -> heroesLoadState.refresh as LoadState.Error
            heroesLoadState.prepend is LoadState.Error -> heroesLoadState.prepend as LoadState.Error
            heroesLoadState.append is LoadState.Error -> heroesLoadState.append as LoadState.Error
            else -> null
        }
        when {
            heroesLoadState.refresh is LoadState.Loading -> {
                ShimmerItemsList(
                    modifier = modifier
                )
            }
            error != null -> {
                val errorMessage = when(error.error) {
                    is SocketTimeoutException -> {
                        R.string.server_error_message
                    }
                    is ConnectException -> {
                        R.string.connection_error_message
                    }
                    else -> {
                        R.string.unknown_error_message
                    }
                }
                val errorImage = when(error.error) {
                    is SocketTimeoutException -> {
                        R.drawable.error_server
                    }
                    is ConnectException -> {
                        R.drawable.error_connection
                    }
                    else -> {
                        R.drawable.error_unknown
                    }
                }
                ErrorDisplay(
                    modifier = modifier,
                    heroes = heroes,
                    message = errorMessage,
                    image = errorImage
                )
            }
            heroes.itemCount <= 0 -> {
                EmptyDisplay(
                    modifier = modifier,
                    message = R.string.empty_search_result_message,
                    image = R.drawable.search_empty_result
                )
            }
            else -> {
                HeroItemsList(
                    modifier = modifier,
                    heroes = heroes,
                    onHeroClick = onHeroClick
                )
            }
        }
    } else {
        EmptyDisplay(
            modifier = modifier,
            message = R.string.search_hero,
            image = R.drawable.search
        )
    }
}