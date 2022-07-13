package com.example.parkingspotfinder.domain.repo

import com.example.parkingspotfinder.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepo {

    suspend fun insertParkingSpot(spot: ParkingSpot)
    suspend fun deleteParkingSpot(spot: ParkingSpot)
    fun getAllParkingSpots(): Flow<List<ParkingSpot>>
}