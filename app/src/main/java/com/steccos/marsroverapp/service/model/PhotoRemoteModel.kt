package com.steccos.marsroverapp.service.model

import com.google.gson.annotations.SerializedName

data class PhotoRemoteModel(
    val camera: CameraRemoteModel,
    @field:SerializedName("earth:date") val earthDate: String,
    val id: Int,
    @field:SerializedName("img_src") val imgSrc: String,
    val rover: RoverRemoteModel,
    val sol: Int
)
