package com.example.profile.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.profile.ui.navigation.ScreenRoute
import com.example.profile.ui.theme.Black2
import com.example.profile.ui.theme.InterFontFamily

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.Black) },
            label = { Text("Home",fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp,color = Black2) },
            selected = navController.currentBackStackEntry?.destination?.route == ScreenRoute.Home.route,
            onClick = { navController.navigate(ScreenRoute.Home.route) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Outlined.StarBorder, contentDescription = "Favorites", tint = Color.Black) },
            label = { Text("Favorites", fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp,color = Black2) },
            selected = false,
            onClick = { /* Manejar navegación */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Black) },
            label = { Text("Search", fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp,color = Black2) },
            selected = false,
            onClick = { navController.navigate(ScreenRoute.Search.route) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.Black) },
            label = { Text("Profile", fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp,color = Black2) },
            selected = navController.currentBackStackEntry?.destination?.route == ScreenRoute.Profile.route,
            onClick = { navController.navigate(ScreenRoute.Profile.route) }
        )
    }
}
