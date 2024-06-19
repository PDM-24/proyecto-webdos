package com.example.profile.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profile.model.SuggestionDataModel
import com.example.profile.ui.theme.Black
import com.example.profile.ui.theme.Black2
import com.example.profile.ui.theme.Gray4
import com.example.profile.ui.theme.InterFontFamily
import com.example.profile.ui.theme.LightGray3
import com.example.profile.ui.theme.Yellow
import com.example.profile.R

@Composable
fun SuggestionComponent(suggestion: SuggestionDataModel) {
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
                painter = painterResource(id = suggestion.photo_id),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 166.dp, height = 146.dp)
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
fun RatingsComponentPreview() {
    MaterialTheme {
        SuggestionComponent(SuggestionDataModel(1, R.drawable.burgerking, "Ratings", 4.0, 700, 700))
    }
}
