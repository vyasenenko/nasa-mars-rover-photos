package com.yasenenko.nasa.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CameraX(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String
) : Parcelable