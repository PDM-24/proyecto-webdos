package com.example.profile.ui.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.profile.MainViewModel
import com.example.profile.ui.Component.BottomNavigationBar
import com.example.profile.ui.Component.TopBar
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    onMapLoadedListener: () -> Unit = {},
    content:@Composable () -> Unit = {}
) {
    val ubi = LatLng(13.6633407, -89.2171927)
    val defaultCamaraPosition = CameraPosition.fromLatLngZoom(ubi, 14f)
    val cameraPositionStata = rememberCameraPositionState {
        position = defaultCamaraPosition
    }
    Scaffold(
        topBar = {
            TopBar(title = "Search", navController = navController)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()){
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionStata,
                // onMapLoaded = onMapLoaded,

            )
        }


    }


}