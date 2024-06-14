package com.example.profile.ui.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.profile.ui.navigation.ScreenRoute


@Composable
fun TopBar(
    title: String = "",
    navController: NavController,
    onSaveEvent: () -> Unit = {},
    onDeleteEvent: () -> Unit = {}
) {
    // Calculando la ruta actual
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Determinar si se debe mostrar la TopBar
    val showTopBar = currentRoute != ScreenRoute.Profile.route

    // Verificar si se debe mostrar la TopBar
    if (showTopBar) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFFEB445B)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentRoute != ScreenRoute.Home.route) {
                Icon(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .fillMaxHeight()
                        .clickable { navController.popBackStack() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White
                )
            }
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = title,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))

            }
        }
    }



