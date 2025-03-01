package com.example.profile.ui.screen0

import android.os.Handler
import android.os.Looper
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.R
import com.example.profile.UiState
import com.example.profile.data.api.LoginApi
import com.example.profile.ui.component.LoadingProgressDialog
import com.example.profile.ui.navigation.ScreenRoute
import com.example.profile.ui.theme.InterFontFamily

@OptIn(ExperimentalMaterial3Api::class)
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

    val keyboardController = LocalSoftwareKeyboardController.current

    val logInScreenState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (logInScreenState) {
        is UiState.Error -> {
            val message = (logInScreenState as UiState.Error).msg
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            viewModel.setStateToReady()
        }
        UiState.Loading -> {
            LoadingProgressDialog()
        }
        UiState.Ready -> {}
        is UiState.Success -> {
            val message = (logInScreenState as UiState.Success).msg
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            viewModel.setStateToReady()
            navController.navigate(ScreenRoute.Home.route)
        }
    }

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
            Text(
                text = "Email",
                modifier = Modifier.align(Alignment.Start),
                fontFamily = InterFontFamily,
                fontSize = 16.sp,
                color = Color(0x9F1E1E1E),
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        "nombre.apellido@empresa.com",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0x80D9D9D9), shape = RoundedCornerShape(50)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "Password",
                modifier = Modifier.align(Alignment.Start),
                fontFamily = InterFontFamily,
                fontSize = 16.sp,
                color = Color(0x9F1E1E1E),
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
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
                            tint = Color.Gray
                        )
                    }
                },
                placeholder = {
                    Text(
                        "********",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0x80D9D9D9), shape = RoundedCornerShape(50)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(80.dp))
            Button(
                onClick = {
                    keyboardController?.hide()
                    viewModel.logIn(
                        LoginApi(
                            email = email,
                            password = password
                        )
                    )
                },
                enabled = isLoginEnabled,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLoginEnabled) Color(0xFFF6A0A0) else Color(0xFFF6A0A0) ,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Forgot password",
                color = Color.Gray,
                modifier = Modifier.clickable { /* Navegar a recuperación de contraseña */ }
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Don't have an account?",
                    color = Color(0x9F1E1E1E))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Register",
                    color = Color(0xFFEB445B),
                    modifier = Modifier.clickable { navController.navigate(ScreenRoute.Register.route) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val dummyViewModel = MainViewModel() // Instancia válida de MainViewModel
    LoginScreen(viewModel = dummyViewModel, navController = navController)
}