package com.yasenenko.nasa.domain.api

import com.yasenenko.nasa.domain.model.PhotosModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    fun getList(
        @Query("sol") sol: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<PhotosModel>
}