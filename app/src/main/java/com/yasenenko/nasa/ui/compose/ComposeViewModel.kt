package com.yasenenko.nasa.ui.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.yasenenko.nasa.common.CommonViewModel
import com.yasenenko.nasa.common.NetworkConnectionLiveData
import com.yasenenko.nasa.common.zip
import com.yasenenko.nasa.domain.comparator.PhotoSortEnum
import com.yasenenko.nasa.domain.model.Photo
import com.yasenenko.nasa.domain.repository.NasaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.gildor.coroutines.retrofit.getOrThrow
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor() : CommonViewModel() {

    @Inject
    lateinit var repository: NasaRepository

    @Inject
    lateinit var networkConnection: NetworkConnectionLiveData

    private val sort = MutableLiveData(PhotoSortEnum.ASC)

    private val _photos = MutableLiveData<List<Photo>>()

    val photos: LiveData<List<Photo>?>
        get() = _photos.zip(sort).map { (list, sort) ->
            list.sortedWith(sort.comparator)
        }

    fun fetchPhotos() = tryHttp {
        val model = repository.getList(1000, 2).getOrThrow()
        _photos.postValue(model.photos)
    }


    fun changeSort() {
        if (sort.value == PhotoSortEnum.ASC) {
            sort.postValue(PhotoSortEnum.DESC)
        } else {
            sort.postValue(PhotoSortEnum.ASC)
        }
    }
}