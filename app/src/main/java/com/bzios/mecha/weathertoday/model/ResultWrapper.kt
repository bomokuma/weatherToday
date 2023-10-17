package com.bzios.mecha.weathertoday.model

import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

sealed class ResultWrapper<out T> {

    data class Success<T>(val value: BaseApiResponse<T>) :
        ResultWrapper<BaseApiResponse<T>>()

    data class GenericError(val code: Int, val exception: Exception?) : ResultWrapper<Nothing>()

    data class NetworkError(val exception: Exception?) : ResultWrapper<Nothing>()
}

suspend fun <T> callApiPortal(
    dispatcher: CoroutineDispatcher,
    baseApiResponse: suspend () -> BaseApiResponse<T>,
): ResultWrapper<BaseApiResponse<T>> = withContext(dispatcher) {
    try {
        ResultWrapper.Success(baseApiResponse.invoke())
    } catch (throwable: Throwable) {
        Timber.e(throwable)
        when (throwable) {
            is IOException -> {
                ResultWrapper.NetworkError(throwable)
            }
            is HttpException -> {
                val httpCode = throwable.code()
                ResultWrapper.GenericError(httpCode, throwable)
            }
            is JsonParseException -> {
                ResultWrapper.GenericError(-1, throwable)
            }
            else -> {
                ResultWrapper.GenericError(-1, null)
            }
        }
    }
}
