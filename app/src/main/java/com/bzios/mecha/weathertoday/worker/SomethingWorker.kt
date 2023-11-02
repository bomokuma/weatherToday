package com.bzios.mecha.weathertoday.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.bzios.mecha.weathertoday.impl.YoutubeRepositoryImpl
import com.bzios.mecha.weathertoday.model.BaseApiResponse
import com.bzios.mecha.weathertoday.model.ResultWrapper
import com.bzios.mecha.weathertoday.model.Youtube
import com.bzios.mecha.weathertoday.networking.PortalService
import com.bzios.mecha.weathertoday.networking.service.YoutubeApiService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SomethingWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private fun tag(): String = SomethingWorker::class.java.simpleName

    companion object {
        const val CONST_INPUT_VALUE = "jfsknglskngfsngfskg"
        const val CONST_OUTPUT_VALUE = "fjdkfjdfkdjfdkf"
    }

    private var youtubeRepositoryImpl: YoutubeRepositoryImpl

    init {
        val portalService = PortalService.createPortal(YoutubeApiService::class.java)
        youtubeRepositoryImpl = YoutubeRepositoryImpl(portalService)
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val inputTag = inputData.getString(CONST_INPUT_VALUE)
        when (val result = youtubeRepositoryImpl.getSimpleList()) {
            is ResultWrapper.Success -> {
                val response = result.value
                val stringJson = Gson().toJson(response)
                val outputData = createOutputData(stringJson)
                Result.success(outputData)
            }

            is ResultWrapper.GenericError -> {
                val errorOutput = createOutputData("Error Generic Error")
                Result.failure(errorOutput)
            }

            is ResultWrapper.NetworkError -> {
                val errorOutput = createOutputData("Error Network Error")
                Result.failure(errorOutput)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun createOutputData(output: String): Data = Data.Builder()
        .putString(CONST_OUTPUT_VALUE, output)
        .build()
}