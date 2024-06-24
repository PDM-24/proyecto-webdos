package com.example.profile.ui.Screen

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.profile.ui.navigation.ScreenRoute
import com.example.profile.ui.theme.InterFontFamily

fun getPreferencesStarbucks(context: Context): SharedPreferences {
    return context.getSharedPreferences("comment_prefs_starbucks", Context.MODE_PRIVATE)
}

fun saveRatingStarbucks(context: Context, username: String, rating: Int) {
    val prefs = getPreferencesStarbucks(context)
    with(prefs.edit()) {
        putInt("${username}_rating", rating)
        apply()
    }
}

fun loadRatingStarbucks(context: Context, username: String): Int {
    val prefs = getPreferencesStarbucks(context)
    return prefs.getInt("${username}_rating", 0)
}

fun saveLikeStateStarbucks(context: Context, username: String, liked: Boolean) {
    val prefs = getPreferencesStarbucks(context)
    with(prefs.edit()) {
        putBoolean("${username}_liked", liked)
        apply()
    }
}

fun loadLikeStateStarbucks(context: Context, username: String): Boolean {
    val prefs = getPreferencesStarbucks(context)
    return prefs.getBoolean("${username}_liked", false)
}

fun saveCommentAndRatingStarbucks(context: Context, comment: String, rating: Int) {
    val prefs = getPreferencesStarbucks(context)
    val commentsAndRatings = loadCommentsAndRatingsStarbucks(context).toMutableList()
    commentsAndRatings.add(Pair(comment, rating)) // Agregar el comentario y la calificación juntos
    val commentsSet = commentsAndRatings.map { "${it.first},${it.second}" }.toSet() // Convertir la lista a un conjunto
    with(prefs.edit()) {
        putStringSet("comments", commentsSet)
        apply()
    }
}

fun loadCommentsAndRatingsStarbucks(context: Context): List<Pair<String, Int>> {
    val prefs = getPreferencesStarbucks(context)
    val commentsSet = prefs.getStringSet("comments", setOf()) ?: setOf()
    return commentsSet.map { comment ->
        val parts = comment.split(",")
        Pair(parts[0], parts[1].toInt()) // Dividir el comentario y la calificación
    }
}

fun clearCommentsStarbucks(context: Context) {
    val prefs = getPreferencesStarbucks(context)
    with(prefs.edit()) {
        remove("comments")
        apply()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreenStarbucks(viewModel: MainViewModel, navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val commentsAndRatings = remember { mutableStateListOf(*loadCommentsAndRatingsStarbucks(context).toTypedArray()) }
    val ratings = remember { mutableStateMapOf(
        5 to 3,  // Ejemplo de datos, en un caso real estos se cargarían desde preferencias u otra fuente de datos
        4 to 1,
        3 to 0,
        2 to 1,
        1 to 1
    ) }
    var showCommentDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Espacio vacío para bajar el texto "Comments"
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color(0xFFEB445B),
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(5.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
                    .padding(7.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                item {
                    // Texto "Comments" después del espaciado
                    Text(
                        text = "Comments",
                        color = Color(0xFFEB445B),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.starbucks),
                            contentDescription = "Logo Starbucks",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = RoundedCornerShape(18.dp))
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Starbucks",
                            fontSize = 20.sp,
                            color = Color.DarkGray,
                            fontFamily = InterFontFamily
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.starbucks3),
                            contentDescription = "Café Starbucks",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = RoundedCornerShape(45.dp)).clickable(onClick = {
                                    navController.navigate(
                                        ScreenRoute.PhotosST.route
                                    )
                                })
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    RatingSummaryStarbucks(ratings = ratings)
                }

                item {
                    // Botón para añadir un comentario
                    Button(onClick = { showCommentDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                        Text(text = "Añadir comentario", color = Color.Gray)
                    }
                }

                // Mostrar comentarios guardados
                items(commentsAndRatings) { (comment, rating) ->
                    CommentWithLikeCounterStarbucks(
                        context = context,
                        username = "username_example",
                        comment = comment,
                        rating = rating,
                        onRatingChanged = { newRating ->
                            ratings[newRating] = ratings.getOrDefault(newRating, 0) + 1
                            saveRatingStarbucks(context, "username_example", newRating)
                        }
                    )
                }

                item {
                    Button(onClick = {
                        clearCommentsStarbucks(context)
                        commentsAndRatings.clear()
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                        Text(text = "Eliminar todos los comentarios", color = Color.Gray)
                    }
                }
            }
        }
    )

    if (showCommentDialog) {
        AddCommentDialogStarbucks(
            onDismiss = { showCommentDialog = false },
            onSave = { comment, rating ->
                commentsAndRatings.add(Pair(comment, rating))
                saveCommentAndRatingStarbucks(context, comment, rating)
                ratings[rating] = ratings.getOrDefault(rating, 0) + 1
                showCommentDialog = false
            }
        )
    }
}

@Composable
fun AddCommentDialogStarbucks(onDismiss: () -> Unit, onSave: (String, Int) -> Unit) {
    var commentText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Nuevo comentario") },
        text = {
            Column {
                TextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    label = { Text(text = "Escribe tu comentario") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                StarRatingStarbucks(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    rating = rating,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(commentText, rating)
                commentText = ""
                rating = 0
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray))
            {
                Text(text = "Guardar comentario", color = Color.White)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                Text(text = "Cancelar", color = Color.White)
            }
        }
    )
}

@Composable
fun StarRatingStarbucks(
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    rating: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    var rated by remember { mutableStateOf(false) }

    Row(modifier = modifier) {
        for (i in 1..starCount) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = if (i <= rating) "Filled Star" else "Empty Star",
                tint = if (i <= rating) Color(0xFFFFC700) else Color.Gray,
                modifier = Modifier
                    .size(36.dp) // Tamaño más grande para mejor visualización
                    .padding(4.dp) // Espaciado entre las estrellas
                    .clickable(enabled = !rated) {
                        onRatingChanged(i)
                        rated = true
                    }
            )
        }
    }
}

@Composable
fun CommentWithLikeCounterStarbucks(
    context: Context,
    username: String,
    comment: String,
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    var liked by remember { mutableStateOf(loadLikeStateStarbucks(context, username)) }
    var likeCount by remember { mutableStateOf(if (liked) 1 else 0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = comment,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            StarRatingStarbucks(
                modifier = Modifier.align(Alignment.Start),
                rating = rating,
                onRatingChanged = onRatingChanged
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Like",
                    tint = if (liked) Color.Red else Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            liked = !liked
                            likeCount += if (liked) 1 else -1
                            saveLikeStateStarbucks(context, username, liked)
                        }
                )
                Spacer(modifier = Modifier.width(4.dp))
                /*Text(
                    text = likeCount.toString(),
                    fontSize = 16.sp,
                    color = if (liked) Color.Red else Color.Gray
                )*/
            }
        }
    }
}

@Composable
fun RatingSummaryStarbucks(
    ratings: Map<Int, Int> // Un mapa donde la clave es el número de estrellas y el valor es la cantidad de calificaciones
) {
    Column(modifier = Modifier.padding(16.dp)
    ) {
        // Mostrar la calificación promedio
        val totalRatings = ratings.values.sum()
        val averageRating = if (totalRatings > 0) {
            ratings.entries.sumOf { it.key * it.value } / totalRatings.toFloat()
        } else {
            0f
        }

        Text(
            text = String.format("%.1f", averageRating),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            repeat(5) { index ->
                val starRating = index + 1
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = if (averageRating >= starRating) Color(0xFFFFC700) else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        ratings.toSortedMap(reverseOrder()).forEach { (stars, count) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "$stars ")
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "$stars stars",
                    tint = Color(0xFFFFC700),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                LinearProgressIndicator(
                    progress = count / totalRatings.toFloat(),
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFF6A0A0)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "$count")
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentScreenStarbucks() {
    val viewModel = MainViewModel()
    val navController = rememberNavController()
    CommentScreenStarbucks(viewModel, navController)
}