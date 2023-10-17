package com.bzios.mecha.weathertoday.ui.main

import com.bzios.mecha.weathertoday.impl.YoutubeRepositoryImpl
import com.bzios.mecha.weathertoday.model.ResultWrapper
import com.bzios.mecha.weathertoday.networking.PortalService
import com.bzios.mecha.weathertoday.networking.service.YoutubeApiService
import com.bzios.mecha.weathertoday.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel : BaseViewModel<MainNavigator>(), CoroutineScope {

    override fun tag(): String = MainViewModel::class.java.simpleName

    private val job: Job by lazy { SupervisorJob() }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val navigator: MainNavigator? by lazy { getNavigator()?.get() }

    private var youtubeRepositoryImpl: YoutubeRepositoryImpl

    init {
        val youtubeApiService = PortalService.createPortal(YoutubeApiService::class.java)
        youtubeRepositoryImpl = YoutubeRepositoryImpl(youtubeApiService)
    }

    fun getSimpleList() {
        launch {
            navigator?.showLoading()
            when (val result = youtubeRepositoryImpl.getSimpleList()) {
                is ResultWrapper.Success -> {
                    navigator?.hideLoading()
                    val data = result.value
                    if (!data.success) {
                        navigator?.getSimpleListFailed(Exception("Error failed"))
                        return@launch
                    }
                    val datalist = data.data
                    datalist?.let { dataYoutubeList ->
                        navigator?.getSimpleListSuccess(dataYoutubeList)
                    } ?: run {
                        navigator?.getSimpleListFailed(Exception("Data null"))
                    }
                }

                is ResultWrapper.GenericError -> {
                    navigator?.hideLoading()
                    val exception = result.exception ?: Exception("ERRR")
                    navigator?.getSimpleListFailed(exception)
                }

                is ResultWrapper.NetworkError -> {
                    navigator?.hideLoading()
                    val exception = result.exception ?: Exception("ERRR")
                    navigator?.getSimpleListFailed(exception)
                }
            }
        }
    }
}