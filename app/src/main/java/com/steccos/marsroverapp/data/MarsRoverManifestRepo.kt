package com.steccos.marsroverapp.data

import com.steccos.marsroverapp.domain.model.RoverManifestUiState
import com.steccos.marsroverapp.domain.model.toUiModel
import com.steccos.marsroverapp.service.MarsRoverManifestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MarsRoverManifestRepo @Inject constructor(
    private val marsRoverManifestService: MarsRoverManifestService
) {
    fun getManifestRoverManifest(roverName: String): Flow<RoverManifestUiState> = flow {
        try {
            emit(
                toUiModel(
                    marsRoverManifestService.getMarsRoverManifest(
                        roverName.lowercase()
                    )
                )
            )

        }catch (throwable: Throwable) {
            emit(RoverManifestUiState.Error)
        }
    }
}