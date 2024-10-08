package com.yasenenko.nasa.ui.kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.yasenenko.nasa.common.CommonViewModel
import com.yasenenko.nasa.common.NetworkConnectionLiveData
import com.yasenenko.nasa.common.SingleLiveEvent
import com.yasenenko.nasa.common.recycleradapter.RecyclerItem
import com.yasenenko.nasa.common.zip
import com.yasenenko.nasa.common.zipNullable
import com.yasenenko.nasa.domain.comparator.PhotoSortEnum
import com.yasenenko.nasa.domain.model.Photo
import com.yasenenko.nasa.domain.model.PhotosModel
import com.yasenenko.nasa.domain.repository.NasaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.gildor.coroutines.retrofit.getOrThrow
import javax.inject.Inject


@HiltViewModel
class KotlinViewModel @Inject constructor() : CommonViewModel() {

    @Inject
    lateinit var repository: NasaRepository

    @Inject
    lateinit var networkConnection: NetworkConnectionLiveData

    private val photosLiveData: MutableLiveData<PhotosModel> = MutableLiveData()

    private val sort: MutableLiveData<PhotoSortEnum> = MutableLiveData(PhotoSortEnum.ASC)

    val itemClick: SingleLiveEvent<Photo> = SingleLiveEvent()

    val list: LiveData<List<RecyclerItem>>
        get() = photosLiveData.zip(sort).map { (photos, sort) ->
            photos.photos.sortedWith(sort.comparator).map {
                PhotoItemViewModel(it).apply {
                    this.setOnItemClickListener { _, item ->
                        itemClick(item)
                    }
                }.toRecycleItem()
            }
        }

    val showNoNetwork: LiveData<Boolean>
        get() = networkConnection.zipNullable(photosLiveData).map { (hasNetwork, photos) ->
            if (hasNetwork == null && photos == null) {
                return@map false
            }
            if (hasNetwork == false && photos == null) {
                return@map true
            }
            false
        }

    fun fetchPhotos() = tryHttp {
        val model = repository.getList(1000, 2).getOrThrow()
        photosLiveData.postValue(model)
    }

    fun changeSort() {
        if (sort.value == PhotoSortEnum.ASC) {
            sort.postValue(PhotoSortEnum.DESC)
        } else {
            sort.postValue(PhotoSortEnum.ASC)
        }
    }
}