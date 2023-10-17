package com.bzios.mecha.weathertoday.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseViewModel<*>> : Fragment() {

    abstract fun tag() : String
}