package com.bzios.mecha.weathertoday.impl

import com.bzios.mecha.weathertoday.model.BaseApiResponse
import com.bzios.mecha.weathertoday.model.ResultWrapper
import com.bzios.mecha.weathertoday.model.Youtube
import com.bzios.mecha.weathertoday.model.callApiPortal
import com.bzios.mecha.weathertoday.networking.service.YoutubeApiService
import com.bzios.mecha.weathertoday.repo.YoutubeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class YoutubeRepositoryImpl(
    private val youtubeApiService: YoutubeApiService,
    private val coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : YoutubeRepository {

    override suspend fun getSimpleList(): ResultWrapper<BaseApiResponse<MutableList<Youtube>>> =
        callApiPortal(coroutineContext) {
            youtubeApiService.getSimpleList()
        }
}