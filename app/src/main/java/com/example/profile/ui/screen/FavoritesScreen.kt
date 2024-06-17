package com.example.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.model.FavoritesList
import com.example.profile.ui.component.FavoritesComponent
import com.example.profile.ui.component.TopBar

@Composable
fun FavoritesScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    //innerPadding: PaddingValues
) {
    Scaffold(
        topBar = {
            TopBar(title = "Favorites", navController = navController)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(FavoritesList) { favorite ->
                    FavoritesComponent(favorite = favorite)
                }
            }
        }
    }
}



/*@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    val navController = rememberNavController()
    FavoritesScreen(navController = navController, innerPadding = PaddingValues(0.dp))
}*/
