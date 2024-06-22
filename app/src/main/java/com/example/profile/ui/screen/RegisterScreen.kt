package com.example.profile.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.profile.MainViewModel
import com.example.profile.UiState
import com.example.profile.data.api.UserApi
import com.example.profile.ui.component.LoadingProgressDialog
import com.example.profile.ui.navigation.ScreenRoute
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.profile.ui.theme.InterFontFamily
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import com.example.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: MainViewModel, navController: NavController, modifier: Modifier = Modifier) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val registerScreenState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (registerScreenState) {
        is UiState.Error -> {
            val message = (registerScreenState as UiState.Error).msg
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
            val message = (registerScreenState as UiState.Success).msg
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            viewModel.setStateToReady()
            navController.popBackStack()
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
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Register",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color(0xFFEB5757),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Name",
                modifier = Modifier.align(Alignment.Start),
                fontFamily = InterFontFamily,
                fontSize = 16.sp,
                color = Color(0x9F1E1E1E),
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = {
                    Text(
                        "John",
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
                singleLine = true
            )
            //Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Last name",
                modifier = Modifier.align(Alignment.Start),
                fontFamily = InterFontFamily,
                fontSize = 16.sp,
                color = Color(0x9F1E1E1E),
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = {
                    Text(
                        "Doe",
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
                singleLine = true
            )
            //Spacer(modifier = Modifier.height(10.dp))
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
                singleLine = true
            )
            //Spacer(modifier = Modifier.height(10.dp))
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
            //Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Password confirmation",
                modifier = Modifier.align(Alignment.Start),
                fontFamily = InterFontFamily,
                fontSize = 16.sp,
                color = Color(0x9F1E1E1E),
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        confirmPasswordVisible = !confirmPasswordVisible
                    }) {
                        Icon(
                            painter = if (confirmPasswordVisible)
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

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    keyboardController?.hide()
                    if (password != confirmPassword) {
                        errorMessage = "Passwords do not match"
                    } else {
                        errorMessage = null
                        Log.i("Contra",password)
                        Log.i("Contra",confirmPassword)
                        viewModel.createNewUser(
                            UserApi(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                password = password,
                                confirmPassword = confirmPassword
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6A0A0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Register",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier
                    .padding(vertical = 12.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        fontSize = 16.sp,
                        fontFamily = InterFontFamily,
                        color = Color(0x9F1E1E1E)
                    )
                    Text(
                        text = " Login",
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable {
                                navController.navigate(ScreenRoute.Login.route)
                            },
                        fontSize = 16.sp,
                        fontFamily = InterFontFamily,
                        color = Color(0xFFEB5757)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val dummyViewModel = MainViewModel()
    RegisterScreen(viewModel = dummyViewModel, navController = navController)
}




