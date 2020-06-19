package com.kt.ktapplication.ui.search.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kt.ktapplication.R
import com.kt.ktapplication.mvp.SearchPresenter
import com.kt.ktapplication.mvp.contract.SearchResultContract
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.mvp.model.bean.VideoBean
import com.kt.ktapplication.ui.home.VideoDetailActivity
import com.kt.ktapplication.ui.search.adapter.SearchResultAdapter
import kotlinx.android.synthetic.main.activity_search_list.*
import kotlin.collections.ArrayList

class SearchListActivity : AppCompatActivity(),SearchResultContract.View {
    lateinit var keyWord:String
    lateinit var mPresenter:SearchPresenter
    var mContext: Context = this
    lateinit var adapter:SearchResultAdapter
    var isRefreshing:Boolean = false
    var list:ArrayList<HotBean.ItemListBean> = ArrayList<HotBean.ItemListBean>()
    var start:Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mPresenter = SearchPresenter(mContext, this)
        keyWord = intent?.getStringExtra("query")!!
        toolbar.title = "$keyWord"
        mPresenter.requestData("$keyWord", start)
        adapter = SearchResultAdapter(mContext, list)
        recyclerView.adapter = adapter
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        refreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            start = 10
            isRefreshing = true
            mPresenter.requestData("$keyWord", start)
        })
        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager:LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && list.size - 1 == lastPosition){
                    start = start.plus(10)
                    mPresenter.requestData("$keyWord", start)
                }
            }
        })
    }

    fun onItemClick(view: View){
        var position = recyclerView.getChildPosition(view)
        var photoUrl : String? = list?.get(position)?.data?.cover?.feed
        var intent : Intent = Intent(mContext, VideoDetailActivity::class.java)
        var desc = list?.get(position)?.data?.description
        var playUrl = list?.get(position)?.data?.playUrl
        var blurred = list?.get(position)?.data?.cover?.blurred
        var collect = list?.get(position)?.data?.consumption?.collectionCount
        var share = list?.get(position)?.data?.consumption?.shareCount
        var reply = list?.get(position)?.data?.consumption?.replyCount
        var duration = list?.get(position)?.data?.duration
        var category = list?.get(position)?.data?.category
        var title : String? = list?.get(position)?.data?.title
        var time = System.currentTimeMillis()
        var videoBean  = VideoBean(photoUrl,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)
        intent.putExtra("bean",videoBean as Parcelable)
        mContext.startActivity(intent)

    }

    override fun setData(bean: HotBean) {
        if (isRefreshing){
            list.clear()
        }
        list.addAll(bean.itemList)

        adapter.notifyDataSetChanged()
        refreshLayout.isRefreshing = false
        isRefreshing = false
    }

}
