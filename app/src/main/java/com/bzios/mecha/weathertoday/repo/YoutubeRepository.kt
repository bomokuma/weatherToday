package com.bzios.mecha.weathertoday.repo

import com.bzios.mecha.weathertoday.model.BaseApiResponse
import com.bzios.mecha.weathertoday.model.ResultWrapper
import com.bzios.mecha.weathertoday.model.Youtube

interface YoutubeRepository {

    suspend fun getSimpleList(): ResultWrapper<BaseApiResponse<MutableList<Youtube>>>
}