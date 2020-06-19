package com.kt.ktapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kt.ktapplication.R
import com.kt.ktapplication.base.BaseFragment
import com.kt.ktapplication.ui.adapter.HotPageAdapter
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Created by zz on 2020/5/12.
 */
class HotFragment : BaseFragment() {
    var tabs:MutableList<String> = arrayListOf<String>("周排行","月排行","总排行")
    var fragments:MutableList<Fragment> = arrayListOf()
    override fun initView() {
        var weekRank = RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString("strategy", "weekly")
        weekRank.arguments = weekBundle

        var monthRank = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString("strategy", "monthly")
        monthRank.arguments = monthBundle

        var allRank = RankFragment()
        var allBundle = Bundle()
        allBundle.putString("strategy", "historical")
        allRank.arguments = allBundle

        fragments.add(weekRank)
        fragments.add(monthRank)
        fragments.add(allRank)
        viewPager.adapter = HotPageAdapter(fragmentManager!!, fragments, tabs)
        tab.setupWithViewPager(viewPager)
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_hot
    }

    override fun onFragmentVisibleChange(b: Boolean) {
    }
}