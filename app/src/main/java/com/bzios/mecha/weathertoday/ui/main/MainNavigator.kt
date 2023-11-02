package com.bzios.mecha.weathertoday.ui.main

import com.bzios.mecha.weathertoday.model.Youtube

interface MainNavigator {

    fun showLoading()
    fun hideLoading()

    fun getSimpleList()
    fun getSimpleListSuccess(youtubeList : MutableList<Youtube>)
    fun getSimpleListFailed(exception : Exception)

    fun startWorker(tag : String)
}