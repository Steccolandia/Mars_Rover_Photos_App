package com.steccos.marsroverapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.steccos.marsroverapp.domain.model.RoverPhotoUiState
import com.steccos.marsroverapp.ui.savedList.MarsRoverSavedViewModel

@Composable
fun PhotoListSavedScreen(
    modifier: Modifier,
    marsRoverSavedViewModel: MarsRoverSavedViewModel
) {
    val viewState by marsRoverSavedViewModel.marsPhotoUiSavedState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        marsRoverSavedViewModel.getAllSaved()
    }

    when (val roverPhotoUiState = viewState) {
        RoverPhotoUiState.Error -> Error()
        RoverPhotoUiState.Loading -> Loading()
        is RoverPhotoUiState.Success ->
            PhotoList(
                modifier = modifier,
                roverPhotoUiModelList = roverPhotoUiState.roverPhotoUiModelList,
                onClick = {
                    roverPhotoUiModel ->
                    marsRoverSavedViewModel.changeSaveStatus(roverPhotoUiModel)
                }
            )
    }
}