package com.steccos.marsroverapp.domain.model

import com.steccos.marsroverapp.service.model.ManifestPhotoRemoteModel
import com.steccos.marsroverapp.service.model.PhotoManifestRemoteModel
import com.steccos.marsroverapp.service.model.RoverManifestRemoteModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RoverManifestConvertorTest {
    @Test
    fun should_convert_roverManifestRemoteModel_to_RoverManifestUiState() {
        //Given
        val roverManifestRemoteModel = RoverManifestRemoteModel(
            photoManifest = PhotoManifestRemoteModel(
                    landingDate = "20201-02-18",
                launchDate = "2020-07-30",
                maxDate = "2023-05-19",
                maxSol = "799",
                name = "Perseverance",
                photos = listOf(
                    ManifestPhotoRemoteModel(
                        cameras = listOf("REAR_HAZCAM_LEFT", "REAR_AZCAM_RIGHT"),
                        earthDate = "2021-02-18",
                        sol = 0,
                        totalPhotos = 54
                    ),
                    ManifestPhotoRemoteModel(
                        cameras = listOf("EDL_DDCAM", "EDL_PUCAM"),
                        earthDate = "2021-02-19",
                        sol = 1,
                        totalPhotos = 201
                    )
                ),
                statS = "active",
                totalPhotos = 156687
            )
        )

        //When
        val result = toUiModel(roverManifestRemoteModel)

        //Then
        val expectedResult = RoverManifestUiState.Success(
            listOf(
                RoverManifestUiModel(
                    sol = "1",
                    earthDate = "2021-02-19",
                    photoNumber = "201"
                ),
                RoverManifestUiModel(
                    sol = "0",
                    earthDate = "2021-02-18",
                    photoNumber = "54"
                )

            )
        )


        assertEquals(expectedResult, result)
    }
}