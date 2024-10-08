package com.yasenenko.nasa.domain.model

import com.google.gson.annotations.SerializedName

data class PhotosModel(
    @SerializedName("photos")
    val photos: List<Photo>
)
