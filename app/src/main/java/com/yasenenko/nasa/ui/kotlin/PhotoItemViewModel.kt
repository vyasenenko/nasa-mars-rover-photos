package com.yasenenko.nasa.ui.kotlin

import com.yasenenko.nasa.BR
import com.yasenenko.nasa.R
import com.yasenenko.nasa.common.recycleradapter.ItemViewModel
import com.yasenenko.nasa.common.recycleradapter.RecyclerItem
import com.yasenenko.nasa.common.recycleradapter.comparator.RecyclerItemComparator
import com.yasenenko.nasa.domain.model.Photo


class PhotoItemViewModel(override val item: Photo) :
    ItemViewModel<Photo>(), RecyclerItemComparator {

    val url: String
        get() = item.imgSrc

    val photoId: String
        get() = item.photoId.toString()

    val earthDate: String
        get() = item.earthDate

    override fun isSameItem(other: Any): Boolean {
        if (other is PhotoItemViewModel) {
            return other.item.photoId == item.photoId
        }
        return false
    }

    override fun isSameContent(other: Any): Boolean {
        if (other is PhotoItemViewModel) {
            return other.item.photoId == item.photoId
        }
        return false
    }

    fun toRecycleItem() =
        RecyclerItem(this, R.layout.item_photo, BR.viewModel)
}