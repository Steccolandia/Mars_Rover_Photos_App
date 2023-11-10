package com.steccos.marsroverapp.di

import android.content.Context
import com.steccos.marsroverapp.db.MarsRoverSavedDatabase
import com.steccos.marsroverapp.db.MarsRoverSavedPhotoDao
import com.steccos.marsroverapp.service.MarsRoverManifestService
import com.steccos.marsroverapp.service.MarsRoverPhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMarsRoverManifestService(): MarsRoverManifestService =
        MarsRoverManifestService.create()

    @Provides
    fun provideMarsRoverPhotoService(): MarsRoverPhotoService =
        MarsRoverPhotoService.create()

    @Provides
    fun provideMarsRoverSavedPhotoDao(@ApplicationContext context: Context) : MarsRoverSavedPhotoDao =
        MarsRoverSavedDatabase.getInstance(context).marsRoverSavedPhotoDao()

    }










