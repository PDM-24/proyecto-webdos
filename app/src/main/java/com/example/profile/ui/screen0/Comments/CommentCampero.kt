package com.example.profile.ui.Screen.Comments

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
import com.example.profile.ui.Screen.clearCommentsBurguer
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.navigation.ScreenRoute
import com.example.profile.ui.theme.InterFontFamily

fun getPreferencesCampero(context: Context): SharedPreferences {
    return context.getSharedPreferences("comment_prefs_campero", Context.MODE_PRIVATE)
}

fun saveRatingCampero(context: Context, username: String, rating: Int) {
    val prefs = getPreferencesCampero(context)
    with(prefs.edit()) {
        putInt("${username}_rating", rating)
        apply()
    }
}

fun loadRatingCampero(context: Context, username: String): Int {
    val prefs = getPreferencesCampero(context)
    return prefs.getInt("${username}_rating", 0)
}

fun saveLikeStateCampero(context: Context, username: String, liked: Boolean) {
    val prefs = getPreferencesCampero(context)
    with(prefs.edit()) {
        putBoolean("${username}_liked", liked)
        apply()
    }
}

fun loadLikeStateCampero(context: Context, username: String): Boolean {
    val prefs = getPreferencesCampero(context)
    return prefs.getBoolean("${username}_liked", false)
}

fun saveCommentAndRatingCampero(context: Context, comment: String, rating: Int) {
    val prefs = getPreferencesCampero(context)
    val commentsAndRatings = loadCommentsAndRatingsCampero(context).toMutableList()
    commentsAndRatings.add(Pair(comment, rating)) // Agregar el comentario y la calificación juntos
    val commentsSet = commentsAndRatings.map { "${it.first},${it.second}" }
        .toSet() // Convertir la lista a un conjunto
    with(prefs.edit()) {
        putStringSet("comments", commentsSet)
        apply()
    }
}

fun loadCommentsAndRatingsCampero(context: Context): List<Pair<String, Int>> {
    val prefs = getPreferencesCampero(context)
    val commentsSet = prefs.getStringSet("comments", setOf()) ?: setOf()
    return commentsSet.map { comment ->
        val parts = comment.split(",")
        Pair(parts[0], parts[1].toInt()) // Dividir el comentario y la calificación
    }
}

fun clearCommentsCampero(context: Context) {
    val prefs = getPreferencesCampero(context)
    with(prefs.edit()) {
        remove("comments")
        apply()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreenCampero(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val commentsAndRatings =
        remember { mutableStateListOf(*loadCommentsAndRatingsCampero(context).toTypedArray()) }
    val ratings = remember {
        mutableStateMapOf(
            5 to 3,  // Ejemplo de datos, en un caso real estos se cargarían desde preferencias u otra fuente de datos
            4 to 1,
            3 to 0,
            2 to 1,
            1 to 1
        )
    }
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
        bottomBar = {
            BottomNavigationBar(navController = navController)
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
                            painter = painterResource(id = R.drawable.pollo6),
                            contentDescription = "Logo Campero",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = RoundedCornerShape(18.dp))
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Pollo Campero",
                            fontSize = 19.sp,
                            color = Color.DarkGray,
                            fontFamily = InterFontFamily
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.pollo5),
                            contentDescription = "Restaurant Campero",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = RoundedCornerShape(45.dp))
                                .clickable(onClick = {
                                    navController.navigate(
                                        ScreenRoute.PhotosPC.route
                                    )
                                })

                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    RatingSummary(ratings = ratings)
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Botón para añadir un comentario
                        Button(
                            onClick = { showCommentDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(text = "Añadir comentario", color = Color.Gray)
                        }

                        // Botón para eliminar todos los comentarios
                        Button(
                            onClick = {
                                clearCommentsBurguer(context)
                                commentsAndRatings.clear()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(text = "Eliminar comentarios", color = Color.Gray)
                        }
                    }
                }

                // Mostrar comentarios guardados
                items(commentsAndRatings) { (comment, rating) ->
                    CommentWithLikeCounterCampero(
                        context = context,
                        username = "username_example",
                        comment = comment,
                        rating = rating,
                        onRatingChanged = { newRating ->
                            ratings[newRating] = ratings.getOrDefault(newRating, 0) + 1
                            saveRatingCampero(context, "username_example", newRating)
                        }
                    )
                }
            }
        }
    )

    if (showCommentDialog) {
        AddCommentDialogCampero(
            onDismiss = { showCommentDialog = false },
            onSave = { comment, rating ->
                commentsAndRatings.add(Pair(comment, rating))
                saveCommentAndRatingCampero(context, comment, rating)
                ratings[rating] = ratings.getOrDefault(rating, 0) + 1
                showCommentDialog = false
            }
        )
    }
}

@Composable
fun AddCommentDialogCampero(onDismiss: () -> Unit, onSave: (String, Int) -> Unit) {
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
                StarRatingCampero(
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
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
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
fun StarRatingCampero(
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
fun CommentWithLikeCounterCampero(
    context: Context,
    username: String,
    comment: String,
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    var liked by remember { mutableStateOf(loadLikeStateCampero(context, username)) }
    var likeCount by remember { mutableStateOf(if (liked) 1 else 0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = comment,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.DarkGray
            )
            StarRatingCampero(
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
                            saveLikeStateCampero(context, username, liked)
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
fun RatingSummary(
    ratings: Map<Int, Int> // Un mapa donde la clave es el número de estrellas y el valor es la cantidad de calificaciones
) {
    Column(modifier = Modifier.padding(16.dp)) {
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
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
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
                Text(text = "$stars ", color = Color.DarkGray)
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
                Text(text = "$count", color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentScreenCampero() {
    val viewModel = MainViewModel()
    val navController = rememberNavController()
    CommentScreenCampero(viewModel, navController)
}
