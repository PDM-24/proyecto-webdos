package com.example.profile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profile.ui.theme.InterFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit) {

    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp) // espacio entre el scaffold y el searchBar
    ) {
        Spacer(modifier = Modifier.height(14.dp)) // espacio entre el scaffold y el searchBar x2

        Box(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth(fraction = 0.6f)
                .padding(horizontal = 28.dp)
                .background(Color(0x80D9D9D9), shape = RoundedCornerShape(30.dp))
        ) {
            OutlinedTextField(
                value = searchText.value,
                onValueChange = {
                    searchText.value = it
                    onSearch(it)
                },
                leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
                placeholder = {
                    Text(
                        "Search",
                        fontSize = 12.sp,
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp),
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                singleLine = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    SearchBar(onSearch = {})
}



