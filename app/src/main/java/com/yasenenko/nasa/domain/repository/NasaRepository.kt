package com.yasenenko.nasa.domain.repository

import android.content.Context
import com.yasenenko.nasa.domain.api.NasaApi
import com.yasenenko.nasa.R
import com.yasenenko.nasa.domain.model.PhotosModel
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val nasaApi: NasaApi,
    private val context: Context,
) {

    suspend fun getList(sol: Int, page: Int): Result<PhotosModel> {
        return nasaApi.getList(sol, page, context.getString(R.string.api_key)).awaitResult()
    }
}
