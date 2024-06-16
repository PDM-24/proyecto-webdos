package com.example.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.model.SuggestionList
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.component.SearchBar
import com.example.profile.ui.component.SuggestionComponent
import com.example.profile.ui.component.TopBar
import androidx.compose.foundation.lazy.items


@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController, modifier: Modifier = Modifier, innerPadding: PaddingValues) {

    val searchQuery = remember { mutableStateOf("") }

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
            //.padding(1.dp)
        ) {
            SearchBar(onSearch = { query ->
                searchQuery.value = query
            })
            Spacer(modifier = Modifier.height(0.dp))
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                val filteredSuggestions = SuggestionList.filter {
                    it.ratings.contains(searchQuery.value, ignoreCase = true)
                }
                items(filteredSuggestions) { suggestion ->
                    SuggestionComponent(suggestion = suggestion)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    HomeScreen(viewModel = viewModel, navController = navController, innerPadding = PaddingValues(0.dp))
}

