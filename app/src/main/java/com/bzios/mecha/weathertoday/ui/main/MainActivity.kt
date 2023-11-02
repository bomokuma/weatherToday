package com.bzios.mecha.weathertoday.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State.*
import androidx.work.WorkManager
import com.bzios.mecha.weathertoday.adapter.YoutubeItemAdapter
import com.bzios.mecha.weathertoday.ui.base.BaseActivity
import com.bzios.mecha.weathertoday.databinding.ActivityMainBinding
import com.bzios.mecha.weathertoday.model.BaseApiResponse
import com.bzios.mecha.weathertoday.model.Youtube
import com.bzios.mecha.weathertoday.worker.SomethingWorker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : BaseActivity<MainViewModel>(), MainNavigator {

    override fun tag(): String = MainActivity::class.java.simpleName

    private val mainViewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var mainViewModel: MainViewModel? = null
    private var youtubeListAdapter: YoutubeItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainViewBinding.root)

        mainViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        mainViewModel?.setNavigator(this)

        if (savedInstanceState == null) {
            setUpView()
            initial()
        }
    }

    override fun setUpView() {
        youtubeListAdapter = YoutubeItemAdapter(this@MainActivity)
        mainViewBinding.mainScreenRecyclerView.apply {
            adapter = youtubeListAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun initial() {
        getSimpleList()

        mainViewModel?.youtubeLiveData?.observe(this) { youtubeList ->
            youtubeListAdapter?.submitList(youtubeList)
        }


        //startWorker(tag())
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

    override fun startWorker(tag: String) {
        val inputdata = Data.Builder().apply {
            putString(SomethingWorker.CONST_INPUT_VALUE, tag())
        }

        val constrain = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
        }

        val workerRequest = OneTimeWorkRequestBuilder<SomethingWorker>()
            .setInputData(inputdata.build())
            .setConstraints(constrain.build())
            .build()

        WorkManager.getInstance(this@MainActivity).enqueue(workerRequest)
        WorkManager.getInstance(this@MainActivity).getWorkInfoByIdLiveData(workerRequest.id)
            .observe(this@MainActivity) { workInfo ->
                workInfo?.let { workInfo2 ->
                    when(workInfo2.state){

                        SUCCEEDED -> {
                            val outData = workInfo2.outputData
                            val realData = outData.getString(SomethingWorker.CONST_OUTPUT_VALUE)
                            val data = Gson().fromJson<BaseApiResponse<MutableList<Youtube>>>(realData, object :
                                TypeToken<BaseApiResponse<MutableList<Youtube>>>() {}.type)
                            val list = data.data
                            list?.let { dd->
                                getSimpleListSuccess(dd)
                            }
                            justShowToast(this@MainActivity, realData ?: "just output")
                        }
                        FAILED -> {
                            val errorOutput = workInfo2.outputData
                            val errorString = errorOutput.getString(SomethingWorker.CONST_OUTPUT_VALUE)
                            justShowToast(this@MainActivity, errorString ?: "just Error")
                        }
                    else-> return@observe
                    }
                }?: run {
                    return@observe
                }

            }
    }
}