package com.example.parkingspotfinder.data.repo

import androidx.room.Dao
import com.example.parkingspotfinder.data.ParkingSpotDao
import com.example.parkingspotfinder.data.toParkingSpot
import com.example.parkingspotfinder.data.toParkingSpotEntity
import com.example.parkingspotfinder.domain.model.ParkingSpot
import com.example.parkingspotfinder.domain.repo.ParkingSpotRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepoImpl(val dao: ParkingSpotDao):ParkingSpotRepo {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
       dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getAllParkingSpots(): Flow<List<ParkingSpot>> {
        return dao.getParkingSports().map { it.toParkingSpot() }
    }
}