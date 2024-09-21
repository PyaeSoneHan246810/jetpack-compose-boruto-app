package com.example.borutoapp.presentation.home.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.home.component.HomeTopAppBar
import com.example.borutoapp.presentation.ui.theme.MainAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>
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
        ) {
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun HomeScreenPrev() {
    MainAppTheme {
//        HomeScreen(
//            heroes = LazyPagingItems()
//        )
    }
}