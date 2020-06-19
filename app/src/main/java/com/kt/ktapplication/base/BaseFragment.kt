package com.kt.ktapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by zz on 2020/4/29.
 */
abstract class BaseFragment: Fragment() {
    var rootView:View? = null
    var isFirst:Boolean = false
    var isFragmentVisiable:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null){
            rootView = inflater.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()

    abstract fun getLayoutResources():Int

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            isFragmentVisiable = true
        }
        if (rootView == null){
            return
        }
        if (!isFirst && isFragmentVisiable){
            onFragmentVisibleChange(true)
            return
        }

        if (isFragmentVisiable){
            onFragmentVisibleChange(false)
            isFragmentVisiable = false
        }

    }

    open abstract fun onFragmentVisibleChange(b:Boolean)
}