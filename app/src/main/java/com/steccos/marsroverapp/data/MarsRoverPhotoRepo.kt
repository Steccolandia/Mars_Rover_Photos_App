package com.steccos.marsroverapp.data

import com.steccos.marsroverapp.domain.model.RoverPhotoUiModel
import com.steccos.marsroverapp.domain.model.RoverPhotoUiState
import com.steccos.marsroverapp.service.MarsRoverPhotoService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarsRoverPhotoRepo @Inject constructor (
    private val marsRoverPhotoService: MarsRoverPhotoService
        ) {
    fun getMarsRoverPhoto(roverName: String, sol: String) : Flow<RoverPhotoUiState> = flow {
        try{
            val networkResult = marsRoverPhotoService.getMarsRoverPhotos(roverName, sol)
            emit(RoverPhotoUiState.Success(networkResult.photos.map { photo ->
                RoverPhotoUiModel(
                    id = photo.id,
                    roverName = photo.rover.name,
                    imgSrc = photo.imgSrc,
                    sol = photo.sol.toString(),
                    earthDate = photo.earthDate,
                    cameraFullName = photo.camera.fullName
                )
            }))
        } catch (throwable: Throwable) {
            emit(RoverPhotoUiState.Error)
        }
    }

}