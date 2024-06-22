package com.example.profile.ui.navigation

sealed class ScreenRoute(var route: String) {
    object Session: ScreenRoute("session")
    object Profile: ScreenRoute("profile")
    object Home: ScreenRoute("home")

    object Login: ScreenRoute("Login")

    object Register: ScreenRoute("register")

    object Search: ScreenRoute("SearchMap")

    object Favorite: ScreenRoute("Favorites")
    object Photos : ScreenRoute("photos")

    object Comment : ScreenRoute("Comment")

}