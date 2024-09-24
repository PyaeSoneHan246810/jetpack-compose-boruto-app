package com.example.borutoapp.presentation.details.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.ui.theme.MainAppTheme
import com.example.borutoapp.presentation.ui.theme.PADDING_EXTRA_LARGE
import com.example.borutoapp.presentation.ui.theme.PADDING_LARGE
import com.example.borutoapp.presentation.ui.theme.PADDING_SMALL
import com.example.borutoapp.presentation.ui.theme.SHEET_IMAGE_SIZE

@Composable
fun DetailsBottomSheetContent(
    modifier: Modifier = Modifier,
    hero: Hero,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(PADDING_LARGE)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(SHEET_IMAGE_SIZE),
                painter = painterResource(id = R.drawable.onboarding_shuriken),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(PADDING_LARGE))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = hero.name,
                color = textColor,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
        Spacer(modifier = Modifier.height(PADDING_EXTRA_LARGE))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = Icons.Filled.Bolt,
                iconColor = iconColor,
                bigText = hero.power.toString(),
                smallText = stringResource(id = R.string.power),
                textColor = textColor
            )
            InfoBox(
                icon = Icons.Filled.CalendarMonth,
                iconColor = iconColor,
                bigText = hero.month,
                smallText = stringResource(id = R.string.month),
                textColor = textColor
            )
            InfoBox(
                icon = Icons.Filled.Cake,
                iconColor = iconColor,
                bigText = hero.day,
                smallText = stringResource(id = R.string.birthday),
                textColor = textColor
            )
        }
        Spacer(modifier = Modifier.height(PADDING_EXTRA_LARGE))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(PADDING_SMALL)
        ) {
            Text(
                text = stringResource(id = R.string.about),
                color = textColor,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
            Text(
                modifier = Modifier
                    .alpha(0.8f),
                text = hero.about,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(PADDING_EXTRA_LARGE))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(id = R.string.family),
                items = hero.family,
                textColor = textColor
            )
            OrderedList(
                title = stringResource(id = R.string.abilities),
                items = hero.abilities,
                textColor = textColor
            )
            OrderedList(
                title = stringResource(id = R.string.nature_type),
                items = hero.natureTypes,
                textColor = textColor
            )
        }
        Spacer(modifier = Modifier.height(PADDING_EXTRA_LARGE))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun DetailsBottomSheetContentPrev() {
    MainAppTheme {
        Surface {
            DetailsBottomSheetContent(
                hero = Hero(
                    id = 1,
                    name = "Sasuke",
                    image = "/images/sasuke.jpg",
                    about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                    rating = 5.0,
                    power = 98,
                    day = "23rd",
                    month = "July",
                    family = listOf(
                        "Fugaku",
                        "Mikoto",
                        "Itachi",
                        "Sarada",
                        "Sakura"
                    ),
                    abilities = listOf(
                        "Sharingan",
                        "Rinnegan",
                        "Sussano",
                        "Amateratsu",
                        "Intelligence"
                    ),
                    natureTypes = listOf(
                        "Lightning",
                        "Fire",
                        "Wind",
                        "Earth",
                        "Water"
                    )
                )
            )
        }
    }
}