package com.example.profile.ui.screen0

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.component.TopBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.profile.R
import com.example.profile.ui.component.StarRating
import com.example.profile.ui.navigation.ScreenRoute
import com.example.profile.ui.theme.Black
import com.example.profile.ui.theme.Black2
import com.example.profile.ui.theme.Gray4
import com.example.profile.ui.theme.InterFontFamily
import com.example.profile.ui.theme.LightGray3

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Column(
                    modifier = Modifier
                        // .weight(1f)
                        .padding(end = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.burgerking),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(width = 166.dp, height = 146.dp)
                            .clickable(onClick = { navController.navigate(ScreenRoute.CommentBK.route) })
                    )
                    //Spacer(modifier = Modifier.height(0.dp))
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "Favorite",
                        tint = Black,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.End)
                    )
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Gray4
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(146.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(11.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "ratings",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Black2
                        )
                        Spacer(modifier = Modifier.height(9.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "4.0",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Black2
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        StarRating(rating = 4.0)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "700 reviews",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            color = LightGray3
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Column(
                    modifier = Modifier
                        // .weight(1f)
                        .padding(end = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.starbucks),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(width = 166.dp, height = 146.dp)
                            .clickable(onClick = { navController.navigate(ScreenRoute.CommentST.route) })
                    )
                    //Spacer(modifier = Modifier.height(0.dp))
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "Favorite",
                        tint = Black,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.End)
                    )
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Gray4
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(146.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(11.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "ratings",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Black2
                        )
                        Spacer(modifier = Modifier.height(9.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "4.5",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Black2
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        StarRating(rating = 4.5)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "700 reviews",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            color = LightGray3
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Column(
                    modifier = Modifier
                        // .weight(1f)
                        .padding(end = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.pollocampero),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(width = 166.dp, height = 146.dp)
                            .clickable(onClick = { navController.navigate(ScreenRoute.CommentPC.route) })
                    )
                    //Spacer(modifier = Modifier.height(0.dp))
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "Favorite",
                        tint = Black,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.End)
                    )
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Gray4
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(146.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(11.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "ratings",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Black2
                        )
                        Spacer(modifier = Modifier.height(9.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "3.5",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Black2
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        StarRating(rating = 3.5)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "700 reviews",
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            color = LightGray3
                        )
                    }
                }
            }
        }

        /*Column(
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
        }*/
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

