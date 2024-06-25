package com.example.profile.ui.Screen.Comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.profile.MainViewModel
import com.example.profile.R
import com.example.profile.ui.component.BottomNavigationBar
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosStarbucks(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color(0xFFEB445B),
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(5.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Imagen central
                Image(
                    painter = painterResource(id = R.drawable.starbucks),
                    contentDescription = "Imagen",
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Primera fila de imágenes adicionales
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bebidas), // Reemplaza con el ID real de la imagen
                        contentDescription = "Imagen adicional 1",
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.bebida1), // Reemplaza con el ID real de la imagen
                        contentDescription = "Imagen adicional 2",
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Segunda fila de imágenes adicionales
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cafe), // Reemplaza con el ID real de la imagen
                        contentDescription = "Imagen adicional 3",
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.starbucks3), // Reemplaza con el ID real de la imagen
                        contentDescription = "Imagen adicional 4",
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tercera fila de imágenes adicionales
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.starbuckssssss), // Reemplaza con el ID real de la imagen
                        contentDescription = "Imagen adicional 5",
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.desayuno), // Reemplaza con el ID real de la imagen
                        contentDescription = "Imagen adicional 6",
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PhotosStarbucksPreview() {
    val viewModel = MainViewModel()
    val navController = rememberNavController()
    PhotosStarbucks(viewModel, navController)
}
