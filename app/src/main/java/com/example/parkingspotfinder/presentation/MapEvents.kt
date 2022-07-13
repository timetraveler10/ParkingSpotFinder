package com.example.parkingspotfinder.presentation

import com.example.parkingspotfinder.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvents {
    object ToggleFalloutMap : MapEvents()
    data class onMapLongClick(val latLng: LatLng) : MapEvents()
    data class onInfoWindowLongClick(val spot: ParkingSpot):MapEvents()
}
