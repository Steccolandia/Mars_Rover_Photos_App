package com.steccos.marsroverapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.steccos.marsroverapp.domain.model.RoverPhotoUiState
import com.steccos.marsroverapp.ui.photoList.MarsRoverPhotoViewModel

@Composable
fun PhotoScreen(
    modifier: Modifier,
    roverName: String?,
    sol: String?,
    marsRoverPhotoViewModel: MarsRoverPhotoViewModel
) {
    val viewState by marsRoverPhotoViewModel.roverPhotoUiState.collectAsStateWithLifecycle()

    if (roverName != null && sol != null) {
        LaunchedEffect(Unit) {
            marsRoverPhotoViewModel.getMarsRoverPhoto(roverName, sol)
        }
        when(val roverPhotoUiState = viewState) {
            RoverPhotoUiState.Error -> Error()
            RoverPhotoUiState.Loading -> Loading()
            is RoverPhotoUiState.Success -> PhotoList(
                modifier = modifier,
                roverPhotoUiModelList = roverPhotoUiState.roverPhotoUiModelList) {
                roverPhotoUiModel ->
                marsRoverPhotoViewModel.changeSaveStatus(roverPhotoUiModel)
            }
        }
    }
}