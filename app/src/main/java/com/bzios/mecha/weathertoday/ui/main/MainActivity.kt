package com.bzios.mecha.weathertoday.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bzios.mecha.weathertoday.ui.base.BaseActivity
import com.bzios.mecha.weathertoday.databinding.ActivityMainBinding
import com.bzios.mecha.weathertoday.model.Youtube

class MainActivity : BaseActivity<MainViewModel>(), MainNavigator {

    override fun tag(): String = MainActivity::class.java.simpleName

    private val mainViewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainViewBinding.root)

        mainViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        mainViewModel?.setNavigator(this)

        if (savedInstanceState == null){
            setUpView()
            initial()
        }
    }

    override fun setUpView() {

    }

    override fun initial() {
        getSimpleList()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun getSimpleList() {
        mainViewModel?.getSimpleList()
    }

    override fun getSimpleListSuccess(youtubeList: MutableList<Youtube>) {

    }

    override fun getSimpleListFailed(exception: Exception) {

    }
}