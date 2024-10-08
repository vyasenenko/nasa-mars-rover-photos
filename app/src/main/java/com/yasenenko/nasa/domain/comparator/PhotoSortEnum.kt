package com.yasenenko.nasa.domain.comparator

import com.yasenenko.nasa.domain.model.Photo

enum class PhotoSortEnum(val comparator: Comparator<Photo>) {

    ASC(PhotoAscendingComparator),
    DESC(PhotoDescendingComparator)
}