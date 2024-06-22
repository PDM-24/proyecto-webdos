package com.example.profile.ui.Screen.Comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.profile.ui.component.BottomNavigationBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.profile.MainViewModel
import com.example.profile.R

@Composable
fun PhotosBurguer(viewModel: MainViewModel,
                  navController: NavController,
                  modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Imagen central
        Image(
            painter = painterResource(id = R.drawable.burgerking),
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
                painter = painterResource(id = R.drawable.comida6), // Reemplaza con el ID real de la imagen
                contentDescription = "Imagen adicional 1",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.comida4), // Reemplaza con el ID real de la imagen
                contentDescription = "Imagen adicional 2",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Primera fila de imágenes adicionales
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.comida5), // Reemplaza con el ID real de la imagen
                contentDescription = "Imagen adicional 1",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.cajitafeliz), // Reemplaza con el ID real de la imagen
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
                painter = painterResource(id = R.drawable.lugar), // Reemplaza con el ID real de la imagen
                contentDescription = "Imagen adicional 3",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.logobk), // Reemplaza con el ID real de la imagen
                contentDescription = "Imagen adicional 4",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}



/*@Preview
@Composable
fun PhotosBurguerPreview() {
   PhotosBurguer()
}*/

