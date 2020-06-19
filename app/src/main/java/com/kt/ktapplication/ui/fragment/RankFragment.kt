package com.kt.ktapplication.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kt.ktapplication.R
import com.kt.ktapplication.base.BaseFragment
import com.kt.ktapplication.mvp.RankPresenter
import com.kt.ktapplication.mvp.contract.RankContract
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.ui.adapter.RankAdapter
import kotlinx.android.synthetic.main.fragment_find.*

/**
 * Created by zz on 2020/6/19.
 */
class RankFragment : BaseFragment(),RankContract.View {
    lateinit var mPresenter:RankPresenter
    lateinit var strategy:String
    lateinit var mData:HotBean
    lateinit var mAdapter:RankAdapter
    override fun initView() {
        if (arguments != null){
            strategy = arguments!!.getString("strategy").toString()
        }
        mPresenter = RankPresenter(context!!, this)
        mPresenter.requestData(strategy)
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_rank
    }

    override fun onFragmentVisibleChange(b: Boolean) {

    }

    override fun setData(bean: HotBean) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mData = bean
        mAdapter = RankAdapter(context!!, mData)
        recyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

}