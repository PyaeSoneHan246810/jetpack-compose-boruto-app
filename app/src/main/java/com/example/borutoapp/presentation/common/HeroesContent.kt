package com.example.borutoapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import java.net.ConnectException
import java.net.SocketTimeoutException

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
                errorMessage = errorMessage,
                errorImage = errorImage
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
}