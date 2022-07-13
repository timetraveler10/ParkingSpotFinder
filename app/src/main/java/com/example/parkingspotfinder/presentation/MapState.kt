package com.example.parkingspotfinder.presentation

import com.example.parkingspotfinder.domain.model.ParkingSpot
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(mapStyleOptions = MapStyleOptions(MapStyle.json)),
    val isFalloutMap: Boolean = false ,
    val parkingSpots:List<ParkingSpot> = emptyList()

)
