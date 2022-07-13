package com.example.parkingspotfinder.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parkingspotfinder.data.ParkingSpotDatabase
import com.example.parkingspotfinder.data.repo.ParkingSpotRepoImpl
import com.example.parkingspotfinder.domain.repo.ParkingSpotRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun provideParkingSpotDatabase(application: Application): ParkingSpotDatabase {

        return Room.databaseBuilder(
            application,
            ParkingSpotDatabase::class.java,
            "parking_spot_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepo(database: ParkingSpotDatabase):ParkingSpotRepo{
        return ParkingSpotRepoImpl(dao = database.dao)
    }
}