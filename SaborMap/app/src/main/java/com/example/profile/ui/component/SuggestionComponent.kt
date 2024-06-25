package com.example.profile.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profile.R
import com.example.profile.model.SuggestionDataModel
import com.example.profile.ui.theme.*

@Composable
fun SuggestionComponent(
    suggestion: SuggestionDataModel,
    onClick: () -> Unit,
    updateFavorite: (SuggestionDataModel) -> Unit
) {
    val isFavorite = remember { mutableStateOf(suggestion.isFavorite) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(suggestion.photo_id),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 166.dp, height = 146.dp)
                    .clickable(onClick = onClick)
            )
            Icon(
                imageVector = if (isFavorite.value) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = "Favorite",
                tint = if (isFavorite.value) Yellow else Black,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.End)
                    .clickable {
                        isFavorite.value = !isFavorite.value
                        updateFavorite(suggestion)
                    }
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
                    text = suggestion.ratings,
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
                        text = suggestion.rating.toString(),
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Black2
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                StarRating(rating = suggestion.rating)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${suggestion.reviews} reviews",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = LightGray3
                )
            }
        }
    }
}

@Composable
fun StarRating(rating: Double) {
    Row {
        for (i in 1..5) {
            if (i <= rating) {
                Icon(Icons.Filled.Star, contentDescription = null, tint = Yellow)
            } else {
                Icon(Icons.Outlined.StarBorder, contentDescription = null, tint = Yellow)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuggestionComponentPreview() {
    SuggestionComponent(
        SuggestionDataModel(1, R.drawable.burgerking, "Ratings", 4.0, 700, 700, false),
        onClick = {},
        updateFavorite = {}
    )
}
