package com.yasenenko.nasa.domain.comparator

import com.yasenenko.nasa.domain.model.Photo


object PhotoAscendingComparator : Comparator<Photo> {

    override fun compare(p0: Photo, p1: Photo): Int {
        if (p0.photoId > p1.photoId) {
            return 1
        }
        if (p0.photoId == p1.photoId) {
            return 0
        }
        return -1
    }
}