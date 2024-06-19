package com.example.profile.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.R

@Composable
fun CommentScreen(viewModel: MainViewModel, navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(16.dp)
                .wrapContentWidth(Alignment.Start),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Comments",
                color = Color.Red,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(Alignment.Start)) {
                Image(
                    painter = painterResource(id = R.drawable.logo_mcdonalds),
                    contentDescription = "Logo Mc",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = RoundedCornerShape(18.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "McDonald's",
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.local_image),
                    contentDescription = "Restaurant Mc",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = RoundedCornerShape(45.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Image(
                painter = painterResource(id = R.drawable.estrellas),
                contentDescription = "Rating Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))


            CommentWithLikeCounter(
                username = "LuisaGonzalez00155",
                comment = "Las bebidas de este lugar son muy buenas",
                initialLikeCount = 3
            )
            Spacer(modifier = Modifier.height(16.dp))
            CommentWithLikeCounter(
                username = "JuanPerez12345",
                comment = "El servicio fue excelente y la comida deliciosa.",
                initialLikeCount = 4
            )
        }
    }
}

@Composable
fun CommentWithLikeCounter(username: String, comment: String, initialLikeCount: Int) {
    var likeCount by remember { mutableStateOf(initialLikeCount) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Profile",
            tint = Color.Black,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = username,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                repeat(4) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Half Star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = comment)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Like",
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        likeCount++
                    }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = likeCount.toString())
        }
    }
}

@Composable
@Preview
fun PreviewCommentScreen() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    CommentScreen(viewModel, navController)
}
