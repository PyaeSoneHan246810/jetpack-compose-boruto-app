package com.example.borutoapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.PADDING_LARGE

@Composable
fun ShimmerItemsList(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(PADDING_LARGE),
        verticalArrangement = Arrangement.spacedBy(PADDING_LARGE)
    ) {
        items(2) {
            ShimmerItem()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ShimmerItemsListPrev() {
    MainAppTheme {
        ShimmerItemsList()
    }
}