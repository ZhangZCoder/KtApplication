package com.kt.ktapplication.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by zz on 2020/6/19.
 */
class HotPageAdapter(fm: FragmentManager, fragments:MutableList<Fragment>, titles: MutableList<String>) : FragmentPagerAdapter(fm) {
    var fms:MutableList<Fragment> = fragments
    var tts = titles

    override fun getItem(position: Int): Fragment {
        return fms.get(position)

    }

    override fun getCount(): Int {
        return fms.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tts.get(position)
    }
}