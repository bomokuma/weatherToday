package com.bzios.mecha.weathertoday.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bzios.mecha.weathertoday.ui.Blank2Fragment
import com.bzios.mecha.weathertoday.ui.BlankFragment

class SamplePagerAdapter(fragmentManager : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){

    private val fragmentClasses : Array<Fragment> = arrayOf(
        BlankFragment.newInstance("", ""),
        Blank2Fragment.newInstance("", "")
    )
    override fun getItemCount(): Int = fragmentClasses.size

    override fun createFragment(position: Int): Fragment = fragmentClasses[position]

}