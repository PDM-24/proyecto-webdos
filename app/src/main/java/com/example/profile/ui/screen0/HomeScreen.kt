package com.example.profile.ui.screen0

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    Scaffold(
        topBar = {
            TopBar(title = "Suggestions", navController = navController)
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
            viewModel.suggestionList.forEach { suggestion ->
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    HomeScreen(
        viewModel = viewModel,
        navController = navController,
        innerPadding = PaddingValues(0.dp)
    )
}

