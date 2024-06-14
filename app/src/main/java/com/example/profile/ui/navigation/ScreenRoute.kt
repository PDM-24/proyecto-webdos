package com.example.profile.ui.navigation

sealed class ScreenRoute(var route: String) {
    object Session: ScreenRoute("session")
    object Profile: ScreenRoute("profile")
    object Home: ScreenRoute("home")
}