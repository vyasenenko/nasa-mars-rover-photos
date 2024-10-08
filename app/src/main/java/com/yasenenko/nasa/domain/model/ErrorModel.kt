package com.yasenenko.nasa.domain.model

import android.content.Context
import androidx.annotation.StringRes

data class ErrorModel(
    private val errorText: String? = null,
    @StringRes private val errorRes: Int? = null,
) {

    init {
        if (errorText == null && errorRes == null) {
            error("One field is expected to be filled in")
        }
    }

    fun getMessage(context: Context) = errorText ?: context.getString(errorRes!!)
}