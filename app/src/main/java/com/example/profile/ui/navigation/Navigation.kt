package com.example.profile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.ui.Screen.HomeScreen
import com.example.profile.ui.Screen.ProfileScreen
import com.example.profile.ui.Screen.SessionScreen

@Composable
fun Navigation(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Profile.route,
        modifier = modifier // Pasar el modifier a NavHost
    ) {
        composable(route = ScreenRoute.Session.route) {
            SessionScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Profile.route) {
            ProfileScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Home.route) {
            HomeScreen(viewModel, navController)
        }
    }
}