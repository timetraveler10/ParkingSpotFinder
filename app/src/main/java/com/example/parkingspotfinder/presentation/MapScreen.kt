package com.example.parkingspotfinder.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(viewModel: MapsViewModel = viewModel()) {


    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }


    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.onEvent(MapEvents.ToggleFalloutMap) }) {
            Icon(
                imageVector = if (viewModel.state.isFalloutMap) Icons.Default.ToggleOff else Icons.Default.ToggleOn,
                contentDescription = null
            )
        }
    }) {


        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = { viewModel.onEvent(MapEvents.onMapLongClick(it)) }
        ) {
            viewModel.state.parkingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.lat, spot.lng),
                    title = "Parking Spot (${spot.lat} , ${spot.lng})",
                    snippet = "Snippet",
                    onInfoWindowLongClick = { viewModel.onEvent(MapEvents.onInfoWindowLongClick(spot)) },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }

        }


    }

}