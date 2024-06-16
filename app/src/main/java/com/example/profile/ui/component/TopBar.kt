package com.example.profile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.profile.ui.navigation.ScreenRoute
import com.example.profile.ui.theme.InterFontFamily

@Composable
fun TopBar(
    title: String = "",
    navController: NavController,
    onSaveEvent: () -> Unit = {},
    onDeleteEvent: () -> Unit = {}
) {
    // calculando la ruta actual
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // determina si se debe mostrar la TopBar
    val showTopBar = currentRoute != ScreenRoute.Profile.route

    // verifica si se debe mostrar la TopBar
    if (showTopBar) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .background(Color(0xFFEB445B)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentRoute != ScreenRoute.Home.route) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .fillMaxHeight()
                            .clickable { navController.popBackStack() },
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = title,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}



