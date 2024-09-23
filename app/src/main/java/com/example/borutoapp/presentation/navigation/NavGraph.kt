package com.example.borutoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.details.screen.DetailsScreen
import com.example.borutoapp.presentation.home.screen.HomeScreen
import com.example.borutoapp.presentation.home.viewModel.HomeViewModel
import com.example.borutoapp.presentation.search.screen.SearchScreen
import com.example.borutoapp.presentation.search.viewModel.SearchViewModel
import com.example.borutoapp.presentation.splash.screen.SplashScreen
import com.example.borutoapp.presentation.splash.viewModel.SplashViewModel
import com.example.borutoapp.presentation.welcome.screen.WelcomeScreen
import com.example.borutoapp.presentation.welcome.viewModel.WelcomeViewModel
import com.example.borutoapp.util.Constants
import kotlinx.coroutines.delay

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
            val splashViewModel: SplashViewModel = hiltViewModel()
            var splashLogoVisible by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = true) {
                splashLogoVisible = true
                delay(2000)
                navController.popBackStack()
                navController.navigate(splashViewModel.destinationRoute)
            }
            SplashScreen(
                splashLogoVisible = splashLogoVisible
            )
        }
        composable(route = Screen.Welcome.route) {
            val welcomeViewModel: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(
                onNavigateToHomeScreen = { route ->
                    navController.popBackStack()
                    navController.navigate(route)
                },
                onSaveAppEntryState = welcomeViewModel::onEvent
            )
        }
        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                heroes = homeViewModel.heroes,
                onNavigateToSearchScreen = { route ->
                    navController.navigate(route)
                },
                onNavigateToDetailsScreen = { route ->
                    navController.navigate(route)
                }
            )
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
            DetailsScreen()
        }
        composable(route = Screen.Search.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                searchState = searchViewModel.searchState,
                onSearchQueryUpdated = searchViewModel::onEvent,
                onSearchQueryCleared = searchViewModel::onEvent,
                onSearchHeroes = searchViewModel::onEvent,
                onNavigateToDetailsScreen = { route ->
                    navController.navigate(route)
                }
            )
        }
    }
}