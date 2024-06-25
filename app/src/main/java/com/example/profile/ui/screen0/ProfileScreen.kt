package com.example.profile.ui.screen0

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.profile.MainViewModel
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.component.TopBar
import com.example.profile.ui.navigation.ScreenRoute


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(viewModel: MainViewModel, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBar(title = "Perfil", navController = navController)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
                .padding(innerPadding)
        ) {
            ProfileHeader()
            Spacer(modifier = Modifier.height(20.dp))
            ProfileContent(modifier = Modifier.weight(1f),navController) // Ajustar para ocupar el espacio disponible
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEB445B))
            .padding(24.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(120.dp)
                .background(Color.White, CircleShape),
            tint = Color.Unspecified
        )
        Text("Alejandro Chavez", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun ProfileContent(modifier: Modifier = Modifier,navController: NavController) {
    Column(modifier = modifier.padding(24.dp)) {
        ProfileOption("Username", "Change username")
        ProfileOption("Email", "example@example.com")
        //ProfileOption("Notifications", "Enable notifications")
        Spacer(modifier = Modifier.weight(1f)) // Esto hará que los botones ocupen el espacio disponible
        Button(
            onClick = { navController.navigate(ScreenRoute.Session.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFFEB445B))
        ) {
            Text("Log Out", color = Color.White)
        }
    }
}

@Composable
fun ProfileOption(title: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(title, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Text(value, color = Color.Gray)
    }
}