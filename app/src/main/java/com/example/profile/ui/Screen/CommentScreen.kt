package com.example.profile.ui.Screen

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.R
import com.example.profile.ui.component.BottomNavigationBar
import kotlin.math.roundToInt

fun getPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("comment_prefs", Context.MODE_PRIVATE)
}

fun saveRating(context: Context, username: String, rating: Int) {
    val prefs = getPreferences(context)
    with(prefs.edit()) {
        putInt("${username}_rating", rating)
        apply()
    }
}

fun loadRating(context: Context, username: String): Int {
    val prefs = getPreferences(context)
    return prefs.getInt("${username}_rating", 0)
}

fun saveLikeState(context: Context, username: String, liked: Boolean) {
    val prefs = getPreferences(context)
    with(prefs.edit()) {
        putBoolean("${username}_liked", liked)
        apply()
    }
}

fun loadLikeState(context: Context, username: String): Boolean {
    val prefs = getPreferences(context)
    return prefs.getBoolean("${username}_liked", false)
}

fun saveRatingCount(context: Context, rating: Int, count: Int) {
    val prefs = getPreferences(context)
    with(prefs.edit()) {
        putInt("rating_$rating", count)
        apply()
    }
}

fun loadRatingCount(context: Context, rating: Int): Int {
    val prefs = getPreferences(context)
    return prefs.getInt("rating_$rating", 0)
}

@Composable
fun CommentScreen(viewModel: MainViewModel, navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val ratingCounts = remember { mutableStateListOf(*Array(5) { loadRatingCount(context, it + 1) }) }

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

            // Rating Summary Section
            RatingSummary(context = context, ratingCounts = ratingCounts)

            Spacer(modifier = Modifier.height(16.dp))

            CommentWithLikeCounter(
                context = context,
                username = "LuisaGonzalez00155",
                comment = "Las bebidas de este lugar son muy buenas",
                ratingCounts = ratingCounts
            )
            Spacer(modifier = Modifier.height(16.dp))
            CommentWithLikeCounter(
                context = context,
                username = "JuanPerez12345",
                comment = "El servicio fue excelente y la comida deliciosa.",
                ratingCounts = ratingCounts
            )
        }
    }
}

@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    rating: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    Row(modifier = modifier) {
        for (i in 1..starCount) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = if (i <= rating) "Filled Star" else "Empty Star",
                tint = if (i <= rating) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}

@Composable
fun CommentWithLikeCounter(context: Context, username: String, comment: String, ratingCounts: MutableList<Int>) {
    var rating by remember { mutableStateOf(loadRating(context, username)) }
    var isLiked by remember { mutableStateOf(loadLikeState(context, username)) }

    LaunchedEffect(isLiked, rating) {
        saveLikeState(context, username, isLiked)
        saveRating(context, username, rating)
        updateRatingSummary(context, ratingCounts, rating)
    }

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

            StarRating(
                rating = rating,
                onRatingChanged = { newRating ->
                    ratingCounts[rating - 1] = ratingCounts.getOrNull(rating - 1)?.minus(1) ?: 0
                    ratingCounts[newRating - 1] = ratingCounts.getOrNull(newRating - 1)?.plus(1) ?: 1
                    rating = newRating
                }
            )

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
                tint = if (isLiked) Color.Red else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isLiked = !isLiked
                    }
            )
        }
    }
}

@Composable
fun RatingSummary(context: Context, ratingCounts: List<Int>) {
    val totalRatings = ratingCounts.sum()
    val averageRating = if (totalRatings > 0) {
        ratingCounts.withIndex().sumOf { (index, count) -> (index + 1) * count } / totalRatings.toFloat()
    } else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${averageRating.roundToInt()}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            StarRating(
                rating = averageRating.roundToInt(),
                onRatingChanged = {}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        for (i in 5 downTo 1) {
            RatingBarRow(
                starCount = i,
                percentage = if (totalRatings > 0) (ratingCounts[i - 1] / totalRatings.toFloat()) * 100 else 0f
            )
        }
    }
}

@Composable
fun RatingBarRow(starCount: Int, percentage: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(text = "$starCount ★", modifier = Modifier.width(40.dp))
        LinearProgressIndicator(
            progress = percentage / 100,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp)
        )
        Text(text = "${percentage.roundToInt()}%", modifier = Modifier.width(40.dp))
    }
}

suspend fun updateRatingSummary(context: Context, ratingCounts: MutableList<Int>, newRating: Int) {
    if (newRating == 0) return
    val currentCount = loadRatingCount(context, newRating)
    saveRatingCount(context, newRating, currentCount + 1)
    ratingCounts[newRating - 1] = currentCount + 1
}

@Composable
@Preview
fun PreviewCommentScreen() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    CommentScreen(viewModel, navController)
}