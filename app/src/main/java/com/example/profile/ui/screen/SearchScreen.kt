package com.example.profile.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.profile.MainViewModel
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.component.TopBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("MissingPermission", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    onMapLoadedListener: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                getLastLocation(fusedLocationClient) { location ->
                    location?.let {
                        currentLocation = LatLng(it.latitude, it.longitude)
                        cameraPositionState.position =
                            CameraPosition.fromLatLngZoom(currentLocation!!, 17f)
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLastLocation(fusedLocationClient) { location ->
                    location?.let {
                        currentLocation = LatLng(it.latitude, it.longitude)
                        cameraPositionState.position =
                            CameraPosition.fromLatLngZoom(currentLocation!!, 17f)
                    }
                }
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(title = "Search", navController = navController)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapLoaded = onMapLoadedListener,
                uiSettings = MapUiSettings(
                    myLocationButtonEnabled = true,
                    zoomControlsEnabled = false // Esta línea oculta los botones de zoom
                )
            ) {

                MyLocationOverlay()
            }
        }
    }
}

@Composable
fun MyLocationOverlay() {
    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    var myLocation by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    myLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    myLocation?.let { location ->
        Circle(
            center = location,
            fillColor = Color(0x220000FF), // Color azul con transparencia
            strokeColor = Color(0x220000FF),
            radius = 50.0, // Radio en metros
            strokeWidth = 2f
        )
    }
}

@SuppressLint("MissingPermission")
fun getLastLocation(
    fusedLocationClient: FusedLocationProviderClient,
    callback: (Location?) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        callback(location)
    }
}
