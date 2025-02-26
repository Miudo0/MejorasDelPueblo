package com.empresa.aplicacion.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Overlay


@Composable
fun OpenStreetMap(
    latitud: Double,
    longitud: Double,
    onLocationSelected: (GeoPoint) -> Unit
) {
    var userLocation by remember { mutableStateOf(GeoPoint(latitud, longitud)) }

    AndroidView(
        factory = { context ->
            MapView(context).apply {

                //habilita el zoom y multitouch
                setMultiTouchControls(true)

                //fuente del mapa
                tileProvider.tileSource = TileSourceFactory.MAPNIK

                // Configurar zoom y posición
                val mapController = controller
                mapController.setZoom(19.0)
                mapController.setCenter(userLocation)

                val marker = Marker(this).apply {
                    position = userLocation
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                }
                overlays.add(marker)

                // Configurar el overlay para clicks en el mapa
                overlays.add(object : Overlay() {
                    override fun onSingleTapConfirmed(e: MotionEvent?, mapView: MapView?): Boolean {
                        val projection = mapView?.projection
                        e?.let {
                            val geoPoint = projection?.fromPixels(e.x.toInt(), e.y.toInt()) as GeoPoint
                            // Actualizar la ubicación seleccionada
                            userLocation = geoPoint
                            onLocationSelected(geoPoint)

                            // Actualizar el marcador
                            marker.position = geoPoint
                            mapView.invalidate()
                        }
                        return true
                    }
                })

            }
        },
        update = { mapView ->
            userLocation = GeoPoint(latitud, longitud)
            val mapController = mapView.controller
            mapController.setCenter(userLocation)
            mapController.animateTo(userLocation)
        },
        modifier = Modifier.fillMaxSize()
    )
}
@Composable
fun rememberCurrentLocation(onPermissionGranted: (Boolean) -> Unit): Pair<Double, Double>? {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let {
                    location = Pair(it.latitude, it.longitude)
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startLocationUpdates(fusedLocationClient, locationCallback)
            onPermissionGranted(true)
        } else {
            onPermissionGranted(false)
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            startLocationUpdates(fusedLocationClient, locationCallback)
            onPermissionGranted(true)
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    return location
}

private fun startLocationUpdates(
    fusedLocationClient: FusedLocationProviderClient,
    locationCallback: LocationCallback
) {
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, 5000
    ).apply {
        setMinUpdateIntervalMillis(2000)
        setWaitForAccurateLocation(false)
    }.build()

    try {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    } catch (e: SecurityException) {
        Log.e("Location", "Error de seguridad al solicitar la ubicación: ${e.message}")
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun OpenStreetMapPreview() {
//    OpenStreetMap(latitud = 40.7128, longitud = -74.0060)
//}