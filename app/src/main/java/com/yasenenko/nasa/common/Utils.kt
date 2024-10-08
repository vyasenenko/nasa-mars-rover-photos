package com.yasenenko.nasa.common

import android.os.Build
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.yasenenko.nasa.common.recycleradapter.RecyclerItem
import com.yasenenko.nasa.ui.details.PhotoDetailsFragment.Companion.EXTRA_PHOTO_KEY

internal fun RecyclerItem.bind(binding: ViewDataBinding) {
    val isVariableFound = binding.setVariable(variableId, data)
    if (isVariableFound.not()) {
        throw IllegalStateException(
            buildErrorMessage(variableId, binding)
        )
    }
}

private fun buildErrorMessage(
    variableId: Int, binding: ViewDataBinding
): String {
    val variableName = DataBindingUtil.convertBrIdToString(variableId)
    val className = binding::class.simpleName
    return "Failed to find variable='$variableName' in the following databinding layout: $className"
}


fun <A, B> zipLiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> =
    MediatorLiveData<Pair<A, B>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }

infix fun <A, B> LiveData<A>.zip(b: LiveData<B>): LiveData<Pair<A, B>> = zipLiveData(this, b)

fun <A, B> zipLiveDataNullable(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A?, B?>> =
    MediatorLiveData<Pair<A?, B?>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            this.value = Pair(localLastA, localLastB)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }

infix fun <A, B> LiveData<A>.zipNullable(b: LiveData<B>): LiveData<Pair<A?, B?>> =
    zipLiveDataNullable(this, b)


@Suppress("DEPRECATION")
fun <T> Bundle.getParcelize(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelable(EXTRA_PHOTO_KEY, clazz)
    } else {
        this.getParcelable(EXTRA_PHOTO_KEY)
    }
}


@GlideModule
class AppGlideModule : AppGlideModule()
