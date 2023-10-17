package com.bzios.mecha.weathertoday.ui.base

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseViewModel<*>> : AppCompatActivity() {

    abstract fun tag() : String
    abstract fun setUpView()
    abstract fun initial()

    fun justShowToast(context : Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}