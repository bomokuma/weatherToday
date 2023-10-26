package com.bzios.mecha.weathertoday.ui

import com.bzios.mecha.weathertoday.ui.base.BaseViewModel

class SamplePagerViewModel : BaseViewModel<SamplePagerNavigator>() {

    override fun tag(): String = SamplePagerViewModel::class.java.simpleName
}