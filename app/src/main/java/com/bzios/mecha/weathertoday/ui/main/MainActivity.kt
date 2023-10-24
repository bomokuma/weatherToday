package com.bzios.mecha.weathertoday.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bzios.mecha.weathertoday.adapter.YoutubeItemAdapter
import com.bzios.mecha.weathertoday.ui.base.BaseActivity
import com.bzios.mecha.weathertoday.databinding.ActivityMainBinding
import com.bzios.mecha.weathertoday.model.Youtube

class MainActivity : BaseActivity<MainViewModel>(), MainNavigator {

    override fun tag(): String = MainActivity::class.java.simpleName

    private val mainViewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var mainViewModel: MainViewModel? = null
    private var youtubeListAdapter : YoutubeItemAdapter?=null

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
        youtubeListAdapter = YoutubeItemAdapter(this@MainActivity)
        mainViewBinding.mainScreenRecyclerView.apply {
            adapter = youtubeListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
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
        youtubeListAdapter?.submitList(youtubeList)
        justShowToast(this@MainActivity, "All list count : ${youtubeListAdapter?.currentList}")
    }

    override fun getSimpleListFailed(exception: Exception) {

    }
}