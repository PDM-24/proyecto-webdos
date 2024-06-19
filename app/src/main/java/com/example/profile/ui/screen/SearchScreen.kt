package com.example.profile.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import com.example.profile.R
import com.example.profile.ui.component.BottomNavigationBar
import com.example.profile.ui.component.TopBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

@SuppressLint("MissingPermission", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    onMapLoadedListener: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState()
    var restaurants by remember { mutableStateOf<List<Place>>(emptyList()) }
    val markerIcon = remember {
        mutableStateOf<BitmapDescriptor?>(null)
    }

    // Inicializa Places API si no está inicializado
    if (!Places.isInitialized()) {
        Places.initialize(context.applicationContext, "AIzaSyDCAR5iX4iNFYHsHx1sew4Os51dO8geS2A")
    }
    val placesClient: PlacesClient = Places.createClient(context)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                getLastLocation(fusedLocationClient) { location ->
                    location?.let {
                        currentLocation = LatLng(it.latitude, it.longitude)
                        cameraPositionState.position =
                            CameraPosition.fromLatLngZoom(currentLocation!!, 17f)
                        fetchNearbyRestaurants(placesClient, currentLocation!!) { places ->
                            restaurants = places
                        }
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        MapsInitializer.initialize(context)
        markerIcon.value = bitmapDescriptorFromVector(context, R.drawable.vector_colored_transparent_resized)

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
                        fetchNearbyRestaurants(placesClient, currentLocation!!) { places ->
                            restaurants = places
                        }
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
                restaurants.forEach { place ->
                    place.latLng?.let { latLng ->
                        Marker(
                            state = rememberMarkerState(position = latLng),
                            title = place.name,
                            snippet = place.address,
                            icon = markerIcon.value // Aquí se asigna el ícono personalizado
                        )
                    }
                }
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

@SuppressLint("MissingPermission")
fun fetchNearbyRestaurants(
    placesClient: PlacesClient,
    currentLocation: LatLng,
    callback: (List<Place>) -> Unit
) {
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
    val request = FindCurrentPlaceRequest.newInstance(placeFields)

    placesClient.findCurrentPlace(request).addOnSuccessListener { response ->
        val likelyPlaces = response.placeLikelihoods.mapNotNull { it.place }
        callback(likelyPlaces)
    }.addOnFailureListener { exception ->
        exception.printStackTrace()
        callback(emptyList())
    }
}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
    return if (vectorDrawable == null) {
        null
    } else {
        // Redimensiona el Bitmap a un tamaño más pequeño
        val width = (vectorDrawable.intrinsicWidth * 0.3).toInt() // 50% del tamaño original
        val height = (vectorDrawable.intrinsicHeight * 0.3).toInt() // 50% del tamaño original
        vectorDrawable.setBounds(0, 0, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
