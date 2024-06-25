package com.example.profile.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.ui.Screen.CommentScreen
import com.example.profile.ui.Screen.CommentScreenBurguer
import com.example.profile.ui.Screen.CommentScreenStarbucks
import com.example.profile.ui.Screen.Comments.CommentScreenCampero
import com.example.profile.ui.Screen.Comments.PhotosBurguer
import com.example.profile.ui.Screen.Comments.PhotosCampero
import com.example.profile.ui.Screen.Comments.PhotosStarbucks
import com.example.profile.ui.screen0.FavoritesScreen
import com.example.profile.ui.screen0.HomeScreen
import com.example.profile.ui.screen0.LoginScreen
import com.example.profile.ui.screen0.ProfileScreen
import com.example.profile.ui.screen0.RegisterScreen
import com.example.profile.ui.screen0.SessionScreen
import com.example.profile.ui.screen0.SearchScreen



@SuppressLint("NewApi")
@Composable
fun Navigation(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Session.route,
        modifier = modifier // Pasar el modifier a NavHost
    ) {
        composable(route = ScreenRoute.Session.route) {
            SessionScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Profile.route) {
            ProfileScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Home.route) {
            HomeScreen(viewModel, navController,innerPadding = PaddingValues())
        }
        composable(route = ScreenRoute.Login.route) {
            LoginScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Register.route) {
            RegisterScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Search.route) {
            SearchScreen(viewModel, navController)
        }


        composable(route = ScreenRoute.Favorite.route) {
            FavoritesScreen(viewModel, navController,innerPadding = PaddingValues())
        }

        composable(route = ScreenRoute.Comment.route) {
            CommentScreen(viewModel, navController)
        }
/**/
        composable(route = ScreenRoute.CommentBK.route) {
            CommentScreenBurguer(viewModel, navController)
        }

        composable(route = ScreenRoute.CommentST.route) {
            CommentScreenStarbucks(viewModel, navController)
        }

        composable(route = ScreenRoute.CommentPC.route) {
            CommentScreenCampero(viewModel, navController)
        }

        composable(route = ScreenRoute.PhotosBK.route) {
            PhotosBurguer(viewModel, navController)
        }

        composable(route = ScreenRoute.PhotosST.route) {
            PhotosStarbucks(viewModel, navController)
        }

        composable(route = ScreenRoute.PhotosPC.route) {
            PhotosCampero(viewModel, navController)
        }


    }
}