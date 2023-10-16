package com.bzios.mecha.weathertoday

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseViewModel<*>> : AppCompatActivity() {

    abstract fun tag() : String
    abstract fun setUpView()
    abstract fun initial()
}