package com.kt.ktapplication.ui.fragment

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kt.ktapplication.R
import com.kt.ktapplication.base.BaseFragment
import com.kt.ktapplication.mvp.HomePresenter
import com.kt.ktapplication.mvp.contract.HomeContract
import com.kt.ktapplication.mvp.model.bean.HomeBean
import com.kt.ktapplication.mvp.model.bean.HomeBean.IssueListBean.ItemListBean
import com.kt.ktapplication.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.regex.Pattern

/**
 * Created by zz on 2020/4/30.
 */
class HomeFragment:BaseFragment(),HomeContract.View , SwipeRefreshLayout.OnRefreshListener{
    var mPresent : HomePresenter? = null
    var mIsRefresh: Boolean = false
    var mList = ArrayList<ItemListBean>()
    var mAdapter: HomeAdapter? = null
    var data : String? = null

    override fun initView() {
        mPresent = HomePresenter(context!!, this)
        mPresent?.start()
        mAdapter = HomeAdapter(context!!, mList)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager : LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList.size - 1){
                    if (data != null) {
                        mPresent?.moreData(data!!)
                    }
                }
            }
        })
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun onFragmentVisibleChange(b: Boolean) {
//        TODO("Not yet implemented")
    }

    override fun setData(bean: HomeBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        Log.d("=======data", data)
        if (mIsRefresh){
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }
        bean.issueList.flatMap { it.itemList!! }
            .filter { it.type.equals("video")}
            .forEach { mList.add(it) }

            mAdapter!!.notifyDataSetChanged()
    }

    override fun onRefresh() {
        if (!mIsRefresh){
            mPresent?.start()
            mIsRefresh = true
        }
    }
}