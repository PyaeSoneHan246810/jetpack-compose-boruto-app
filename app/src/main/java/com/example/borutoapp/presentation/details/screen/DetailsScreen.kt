package com.example.borutoapp.presentation.details.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.details.component.DetailsBackgroundContent
import com.example.borutoapp.presentation.details.component.DetailsBottomSheetContent
import com.example.borutoapp.presentation.ui.theme.MINIMUM_SHEET_HEIGHT
import com.example.borutoapp.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    hero: Hero,
    colorPalette: Map<String, String>,
    onNavigateBack: () -> Unit
) {
    val vibrantColor = Color(android.graphics.Color.parseColor(colorPalette[Constants.VIBRANT_COLOR]))
    val darkVibrantColor = Color(android.graphics.Color.parseColor(colorPalette[Constants.DARK_VIBRANT_COLOR]))
    val onDarkVibrantColor = Color(android.graphics.Color.parseColor(colorPalette[Constants.ON_DARK_VIBRANT_COLOR]))
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MINIMUM_SHEET_HEIGHT,
        modifier = modifier
            .fillMaxSize(),
        sheetContent = {
            DetailsBottomSheetContent(
                modifier = Modifier,
                hero = hero,
                iconColor = vibrantColor,
                textColor = onDarkVibrantColor,
            )
        },
        sheetContainerColor = darkVibrantColor,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        DetailsBackgroundContent(
            image = hero.image,
            imageContentDesc = hero.name,
            imageFraction = 1f,
            closeIconColor = vibrantColor,
            onCloseIconButtonClick = onNavigateBack
        )
    }
}