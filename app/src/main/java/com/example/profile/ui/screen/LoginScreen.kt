package com.example.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.profile.MainViewModel
import com.example.profile.R
import com.example.profile.ui.navigation.ScreenRoute

//import com.example.profile.ui.theme.SaborMapTheme
//import com.pescsiete.sabormap.R

@Composable
fun LoginScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val isLoginEnabled = email.isNotBlank() && password.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFEB445B),
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("nombre.apellido@empresa.com") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            painter = if (passwordVisible)
                                painterResource(id = R.drawable.ic_visibility_off)
                            else
                                painterResource(id = R.drawable.ic_visibility_on),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            Button(
                onClick = {navController.navigate(ScreenRoute.Home.route) },
                enabled = isLoginEnabled,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLoginEnabled) Color(0xFFEB445B) else Color(0xFFEB445B) ,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Forgot password",
                color = Color.Gray,
                modifier = Modifier.clickable { /* Navegar a recuperación de contraseña */ }
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Don't have an account?")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Register",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { navController.navigate(ScreenRoute.Register.route) }
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SaborMapTheme {
        LoginScreen()
    }
}*/