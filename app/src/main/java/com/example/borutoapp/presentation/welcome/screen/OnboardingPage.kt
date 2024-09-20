package com.example.borutoapp.presentation.welcome.screen

import androidx.annotation.DrawableRes
import com.example.borutoapp.R

data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val onboardingPages = listOf(
    OnboardingPage(
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!",
        image = R.drawable.onboarding_ninja
    ),
    OnboardingPage(
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about.",
        image = R.drawable.onboarding_kunai
    ),
    OnboardingPage(
        title = "Power",
        description = "Check out your hero's power and see how much are they strong.",
        image = R.drawable.onboarding_shuriken
    )
)
