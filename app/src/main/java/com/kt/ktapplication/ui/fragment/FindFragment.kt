package com.kt.ktapplication.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import com.kt.ktapplication.R
import com.kt.ktapplication.base.BaseFragment
import com.kt.ktapplication.mvp.FindPresenter
import com.kt.ktapplication.mvp.contract.FindContract
import com.kt.ktapplication.mvp.model.bean.FindBean
import com.kt.ktapplication.ui.adapter.FindAdapter
import kotlinx.android.synthetic.main.fragment_find.*

/**
 * Created by zz on 2020/5/12.
 */
class FindFragment : BaseFragment(),FindContract.View {
    lateinit var mPresenter: FindPresenter
    lateinit var adapter:FindAdapter
    var mData: MutableList<FindBean>? = arrayListOf()

    override fun initView() {
        adapter = FindAdapter(context!!, mData!!)
        mPresenter =
            FindPresenter(context!!, this)
        mPresenter.start()
        var gridLayoutManager:GridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_find
    }

    override fun onFragmentVisibleChange(b: Boolean) {
    }

    override fun setData(findBean: MutableList<FindBean>) {
        mData?.addAll(findBean)
        adapter.notifyDataSetChanged()
    }

}