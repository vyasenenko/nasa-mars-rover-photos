package com.yasenenko.nasa.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id")
    val photoId: Int,
    @SerializedName("camera")
    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("img_src")
    val imgSrc: String,
    @SerializedName("rover")
    val rover: Rover,
    @SerializedName("sol")
    val sol: Int
) : Parcelable