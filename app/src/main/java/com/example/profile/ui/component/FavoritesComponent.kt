package com.example.profile.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profile.model.FavoritesDataModel
import com.example.profile.model.FavoritesList
import com.example.profile.ui.theme.Yellow
import com.example.profile.R

@Composable
fun FavoritesComponent(favorite: FavoritesDataModel) {
    val isFavorite = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = favorite.image_id),
            contentDescription = "Logo",
            modifier = Modifier
                .size(width = 166.dp, height = 146.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = if (isFavorite.value) Icons.Filled.Star else Icons.Outlined.StarBorder,
            contentDescription = "Favorite",
            tint = Yellow,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.End)
                .clickable {
                    if (isFavorite.value) {
                        FavoritesList.removeIf { it.id == favorite.id }
                    } else {
                        FavoritesList.add(FavoritesDataModel(favorite.id, favorite.image_id))
                    }
                    isFavorite.value = !isFavorite.value
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesComponentPreview() {
    MaterialTheme {
        FavoritesComponent(FavoritesDataModel(1, R.drawable.burgerking))
    }
}
