package com.steccos.marsroverapp.domain.model

import com.steccos.marsroverapp.service.model.RoverManifestRemoteModel

fun toUiModel(roverManifestRemoteModel: RoverManifestRemoteModel) : RoverManifestUiState =
    RoverManifestUiState.Success(roverManifestRemoteModel.photoManifest.photos.map { photo ->
        RoverManifestUiModel(
    sol = photo.sol.toString(),
    earthDate = photo.earthDate,
        photoNumber = photo.totalPhotos.toString()
        )
    }.sorted())
