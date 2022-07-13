package com.example.parkingspotfinder.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingspotfinder.domain.model.ParkingSpot
import com.example.parkingspotfinder.domain.repo.ParkingSpotRepo
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(val repo: ParkingSpotRepo) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repo.getAllParkingSpots().collectLatest {

                state = state.copy(parkingSpots = it)

            }
        }

    }


    fun onEvent(event: MapEvents) {
        when (event) {
            is MapEvents.ToggleFalloutMap -> {
                state =
                    state.copy(
                        properties = MapProperties(
                            mapStyleOptions = if (state.isFalloutMap) null else MapStyleOptions(
                                MapStyle.json
                            ),
                        ), isFalloutMap = !state.isFalloutMap
                    )
            }

            is MapEvents.onMapLongClick -> {
                viewModelScope.launch {
                    repo.insertParkingSpot(
                        ParkingSpot(
                            lat = event.latLng.latitude,
                            lng = event.latLng.longitude
                        )
                    )
                }
            }
            is MapEvents.onInfoWindowLongClick -> {
                viewModelScope.launch {
                    repo.deleteParkingSpot(event.spot)
                }
            }
        }
    }
}