package com.yasenenko.nasa.ui.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yasenenko.nasa.R
import com.yasenenko.nasa.common.CommonViewModel
import com.yasenenko.nasa.domain.model.Photo


class PhotoDetailsViewModel(val photo: Photo) : CommonViewModel() {

    val photoId: String
        get() = photo.photoId.toString()

    fun details(context: Context): String = context.getString(
        R.string.details,
        "ID${photo.rover.id} ${photo.rover.name}",
        photo.rover.totalPhotos.toString(),
        "ID${photo.camera.id}${photo.camera.fullName} (${photo.camera.name})"
    )

    class Factory(private val photo: Photo) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Photo::class.java)
                .newInstance(photo)
        }
    }
}