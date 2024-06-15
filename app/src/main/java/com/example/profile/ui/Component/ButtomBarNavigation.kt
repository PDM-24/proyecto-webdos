package com.example.profile.ui.Component
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.profile.ui.navigation.ScreenRoute

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.Black) },
            label = { Text("Home", color = Color.Black) },
            selected = navController.currentBackStackEntry?.destination?.route == ScreenRoute.Home.route,
            onClick = { navController.navigate(ScreenRoute.Home.route) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorites", tint = Color.Black) },
            label = { Text("Favorites", color = Color.Black) },
            selected = false,
            onClick = { /* Manejar navegación */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Black) },
            label = { Text("Search", color = Color.Black) },
            selected = false,
            onClick = { navController.navigate(ScreenRoute.Search.route) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Profile", tint = Color.Black) },
            label = { Text("Profile", color = Color.Black) },
            selected = navController.currentBackStackEntry?.destination?.route == ScreenRoute.Profile.route,
            onClick = { navController.navigate(ScreenRoute.Profile.route) }
        )
    }
}

