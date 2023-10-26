package com.bzios.mecha.weathertoday.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bzios.mecha.weathertoday.R
import com.bzios.mecha.weathertoday.adapter.SamplePagerAdapter
import com.bzios.mecha.weathertoday.databinding.ActivitySamplePagerBinding
import com.bzios.mecha.weathertoday.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator

class SamplePagerActivity : BaseActivity<SamplePagerViewModel>(), SamplePagerNavigator {

    override fun tag(): String = SamplePagerActivity::class.java.simpleName

    private val viewBinding: ActivitySamplePagerBinding by lazy {
        ActivitySamplePagerBinding.inflate(layoutInflater)
    }

    private var viewModel: SamplePagerViewModel? = null

    private var pagerAdapter: SamplePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider.NewInstanceFactory().create(SamplePagerViewModel::class.java)
        viewModel?.setNavigator(this)

        if (savedInstanceState == null) {
            setUpView()
            initial()
        }
    }

    override fun setUpView() {
        pagerAdapter = SamplePagerAdapter(supportFragmentManager, lifecycle)
        viewBinding.sampleFrmViewPager?.adapter = pagerAdapter

        TabLayoutMediator(
            viewBinding.historyFrmTabLayout,
            viewBinding.sampleFrmViewPager
        ){ tab, position->
            tab.text = "Tabs + $position"
            tab.icon = ContextCompat.getDrawable(this@SamplePagerActivity, R.mipmap.ic_launcher)
        }.attach()
    }

    override fun initial() {}
}