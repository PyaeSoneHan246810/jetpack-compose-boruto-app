package com.example.borutoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.splash.screen.SplashScreen
import com.example.borutoapp.presentation.welcome.screen.WelcomeScreen
import com.example.borutoapp.util.Constants

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToWelcomeScreen = { route ->
                    navController.navigate(route)
                }
            )
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                onGetStarted = {}
            )
        }
        composable(route = Screen.Home.route) {

        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(
                    name = Constants.ARG_KEY_HERO_ID,
                    builder = {
                        type = NavType.IntType
                    }
                )
            )
        ) {

        }
        composable(route = Screen.Search.route) {

        }
    }
}