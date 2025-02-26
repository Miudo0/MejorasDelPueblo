package com.empresa.aplicacion.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapaSoloLectura() {
    // Obtener la ubicación actual del usuario
    val currentLocation = rememberCurrentLocation { isGranted ->
        if (isGranted) {
            // Si los permisos fueron otorgados, podemos hacer algo con la ubicación
        }
    }

    // Si la ubicación está disponible, mostramos el mapa
    currentLocation?.let { (latitud, longitud) ->
        OpenStreetMapSoloLectura(latitud, longitud)
    } ?: run {
        // Si no se tiene la ubicación, podemos mostrar un mensaje o alguna UI de error
        Text("Esperando ubicación...")
    }
}

@Composable
fun OpenStreetMapSoloLectura(
    latitud: Double,
    longitud: Double
) {
    val userLocation = GeoPoint(latitud, longitud)

    AndroidView(
        factory = { context ->
            MapView(context).apply {

                //habilita el zoom y multitouch, pero sin la opción de seleccionar ubicaciones
                setMultiTouchControls(true)

                //fuente del mapa
                tileProvider.tileSource = TileSourceFactory.MAPNIK

                // Configurar zoom y posición
                val mapController = controller
                mapController.setZoom(19.0)
                mapController.setCenter(userLocation)

                // Añadir el marcador, pero sin funcionalidad de interacción
                val marker = Marker(this).apply {
                    position = userLocation
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                }
                overlays.add(marker)
            }
        },
        update = { mapView ->
            // Actualizar la ubicación en el mapa
            val mapController = mapView.controller
            mapController.setCenter(userLocation)
            mapController.animateTo(userLocation)
        },
        modifier = Modifier.fillMaxSize()
    )
}