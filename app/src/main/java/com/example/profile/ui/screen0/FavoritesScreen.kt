package com.example.profile.ui.screen0

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
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.component.SuggestionComponent
import com.example.profile.ui.component.TopBar
import com.example.profile.ui.navigation.ScreenRoute

@Composable
fun FavoritesScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.favoriteList) { suggestion ->
                    SuggestionComponent(
                        suggestion = suggestion,
                        onClick = {
                            val route = when (suggestion.id) {
                                1 -> ScreenRoute.CommentBK.route
                                2 -> ScreenRoute.CommentST.route
                                3 -> ScreenRoute.CommentPC.route
                                else -> ScreenRoute.CommentBK.route
                            }
                            navController.navigate(route)
                        },
                        updateFavorite = { viewModel.updateFavorite(it) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    FavoritesScreen(
        viewModel = viewModel,
        navController = navController,
        innerPadding = PaddingValues(0.dp)
    )
}
