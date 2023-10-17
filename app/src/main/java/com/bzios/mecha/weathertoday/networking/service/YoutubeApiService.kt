package com.bzios.mecha.weathertoday.networking.service

import com.bzios.mecha.weathertoday.model.BaseApiResponse
import com.bzios.mecha.weathertoday.model.Youtube
import retrofit2.http.GET

interface YoutubeApiService {

    @GET("/simplelist")
    suspend fun getSimpleList(): BaseApiResponse<MutableList<Youtube>>
}