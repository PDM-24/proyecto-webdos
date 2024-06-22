package com.example.profile.ui.screen0

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("MissingPermission", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    onMapLoadedListener: () -> Unit = {},
) {
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState()
    var places by remember { mutableStateOf<List<Place>>(emptyList()) }
    val markerIcon = remember {
        mutableStateOf<BitmapDescriptor?>(null)
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    var selectedPlaceDetails by remember { mutableStateOf<Place?>(null) }
    val scope = rememberCoroutineScope()

    // Inicializa Places API si no está inicializado
    if (!Places.isInitialized()) {
        Places.initialize(context.applicationContext, "YOUR_API_KEY")
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
                        fetchNearbyPlaces(placesClient, currentLocation!!) { fetchedPlaces ->
                            places = fetchedPlaces
                        }
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        MapsInitializer.initialize(context)
        markerIcon.value = bitmapDescriptorFromVector(context, R.drawable.vector_sabormap)

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation(fusedLocationClient) { location ->
                location?.let {
                    currentLocation = LatLng(it.latitude, it.longitude)
                    cameraPositionState.position =
                        CameraPosition.fromLatLngZoom(currentLocation!!, 17f)
                    fetchNearbyPlaces(placesClient, currentLocation!!) { fetchedPlaces ->
                        places = fetchedPlaces
                    }
                }
            }
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            selectedPlaceDetails?.let { PlaceDetailsContent(it, placesClient) }
        },
        sheetPeekHeight = 0.dp // Start with the sheet hidden
    ) {
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
                    places.forEach { place ->
                        place.latLng?.let { latLng ->
                            Marker(
                                state = rememberMarkerState(position = latLng),
                                title = place.name ?: "Unknown Place",
                                snippet = place.address ?: "No Address",
                                icon = markerIcon.value, // Aquí se asigna el ícono personalizado
                                onClick = {
                                    scope.launch {
                                        fetchPlaceDetails(placesClient, place.id) { details ->
                                            selectedPlaceDetails = details
                                            scope.launch {
                                                bottomSheetScaffoldState.bottomSheetState.expand()
                                            }
                                        }
                                    }
                                    true
                                }
                            )
                        }
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
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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
            radius = 259.0, // Radio en metros
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
fun fetchNearbyPlaces(
    placesClient: PlacesClient,
    currentLocation: LatLng,
    callback: (List<Place>) -> Unit
) {
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.TYPES)
    val request = FindCurrentPlaceRequest.newInstance(placeFields)

    placesClient.findCurrentPlace(request).addOnSuccessListener { response ->
        val likelyPlaces = response.placeLikelihoods
            .mapNotNull { it.place }
            .filter { place ->
                place.types?.any { type ->
                    type == Place.Type.RESTAURANT || type == Place.Type.CAFE || type == Place.Type.BAKERY ||
                            type == Place.Type.FOOD || type == Place.Type.MEAL_DELIVERY || type == Place.Type.MEAL_TAKEAWAY ||
                            type == Place.Type.BAR || type == Place.Type.NIGHT_CLUB

                } == true
            }
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

fun fetchPlaceDetails(
    placesClient: PlacesClient,
    placeId: String,
    callback: (Place?) -> Unit
) {
    val placeFields: List<Place.Field> = listOf(
        Place.Field.NAME,
        Place.Field.ADDRESS,
        Place.Field.BUSINESS_STATUS,
        Place.Field.CURRENT_OPENING_HOURS,
        Place.Field.ID,
        Place.Field.OPENING_HOURS,
        Place.Field.UTC_OFFSET,
        Place.Field.PHONE_NUMBER,
        Place.Field.WEBSITE_URI,
        Place.Field.RATING,
        Place.Field.USER_RATINGS_TOTAL,
        Place.Field.ADDRESS_COMPONENTS,
        Place.Field.PHOTO_METADATAS
    )

    val request = FetchPlaceRequest.newInstance(placeId, placeFields)
    placesClient.fetchPlace(request).addOnSuccessListener { response ->
        callback(response.place)
    }.addOnFailureListener { exception ->
        exception.printStackTrace()
        callback(null)
    }
}

fun fetchPhoto(placesClient: PlacesClient, photoMetadata: PhotoMetadata, callback: (Bitmap?) -> Unit) {
    val photoRequest = FetchPhotoRequest.builder(photoMetadata)
        .setMaxWidth(500) // Ajusta el tamaño máximo de la imagen según sea necesario
        .setMaxHeight(500)
        .build()
    placesClient.fetchPhoto(photoRequest).addOnSuccessListener { fetchPhotoResponse ->
        callback(fetchPhotoResponse.bitmap)
    }.addOnFailureListener { exception ->
        exception.printStackTrace()
        callback(null)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlaceDetailsContent(place: Place, placesClient: PlacesClient) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = place.name ?: "No Name ")
        Text(text = place.address ?: "No Address")

        place.phoneNumber?.let {
            Text(text = "Phone: $it")
        }
        place.websiteUri?.let {
            Text(text = "Website: $it")
        }
        place.rating?.let {
            Text(text = "Rating: $it (${place.userRatingsTotal} reviews)")
        }
        place.currentOpeningHours?.let {
            Text(text = "Horarios de apertura: ${it.weekdayText.joinToString(", ")}")
        }

        place.photoMetadatas?.firstOrNull()?.let { photoMetadata ->
            var bitmap by remember { mutableStateOf<Bitmap?>(null) }

            LaunchedEffect(photoMetadata) {
                fetchPhoto(placesClient, photoMetadata) { fetchedBitmap ->
                    bitmap = fetchedBitmap
                }
            }

            bitmap?.let {
                Image(bitmap = it.asImageBitmap(), contentDescription = null, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
