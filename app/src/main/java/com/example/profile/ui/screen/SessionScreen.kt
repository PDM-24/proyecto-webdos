package com.example.profile.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.profile.MainViewModel
import com.example.profile.R
import com.example.profile.ui.navigation.ScreenRoute


@Composable
fun SessionScreen(viewModel: MainViewModel, navController: NavHostController) {
    val openSans = FontFamily(
        Font(R.font.sansitbld, Normal),
        Font(R.font.sansitre, Bold)
    )
    Column(modifier = Modifier.fillMaxWidth())
    {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFEB445B))
                .fillMaxSize()

        ) {
            // Dibujar líneas diagonales
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    color = White,
                    start = androidx.compose.ui.geometry.Offset(size.width *0.3f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                    strokeWidth = 4f
                )
                drawLine(
                    color = White,
                    start = androidx.compose.ui.geometry.Offset(size.width * 0.43f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height * 0.8f),
                    strokeWidth = 4f
                )
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFEB445B))
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Icono",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(121.dp) // Tamaño del icono
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "SaborMap",
                style = TextStyle(
                    fontSize = 65.sp,  // Tamaño de la fuente
                    // Peso de la fuente
                    color = White,  // Color del texto
                    textAlign = TextAlign.Center,
                    fontFamily = openSans,
                    fontWeight = Bold))

        }
        Column(
            modifier = Modifier
                .weight(1F)
                .background(Color(0xFFEB445B))
                .fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextButton(
                onClick = { navController.navigate(ScreenRoute.Login.route) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFEB445B),
                    containerColor = White
                ),
                modifier = Modifier
                    .width(324.dp)
                    .height(55.dp)
                    .border(
                        width = 2.dp,
                        color = White,
                        shape = RoundedCornerShape(22.dp)
                    )
            ){
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 24.sp,  // Tamaño de la fuente
                        fontWeight = Bold,  // Peso de la fuente
                        color = Color(0xFFEB445B),  // Color del texto
                        textAlign = TextAlign.Center  // Alineación del texto
                    ))
            }

            TextButton(
                onClick = { navController.navigate(ScreenRoute.Register.route) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFEB445B),
                    containerColor = White
                ),
                modifier = Modifier
                    .width(324.dp)
                    .height(55.dp)
                    .border(
                        width = 2.dp,
                        color = White,
                        shape = RoundedCornerShape(22.dp)
                    )
            ){
                Text(
                    text = "Register",
                    style = TextStyle(
                        fontSize = 24.sp,  // Tamaño de la fuente
                        fontWeight = Bold,  // Peso de la fuente
                        color = Color(0xFFEB445B),  // Color del texto
                        textAlign = TextAlign.Center  // Alineación del texto
                    ))
            }
        }

        Column(
            modifier = Modifier
                .weight(1F)
                .background(Color(0xFFEB445B))
                .fillMaxSize().padding(8.dp)
        ) {

        }
    }

}