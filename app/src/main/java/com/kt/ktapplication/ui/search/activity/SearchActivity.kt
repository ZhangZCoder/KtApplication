package com.kt.ktapplication.ui.search.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.kt.ktapplication.R
import com.kt.ktapplication.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*


/**
 * Created by zz on 2020/6/15.
 */
class SearchActivity: AppCompatActivity() {
    var data : MutableList<String> = arrayListOf("脱口秀","城会玩","666","笑cry","漫威",
        "清新","匠心","VR","心理学","舞蹈","品牌广告","粉丝自制","电影相关","萝莉","魔性"
        ,"第一视角","教程","毕业设计","奥斯卡","燃","冰与火之歌","温情","线下campaign","公益")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        var mAdapter:SearchAdapter = SearchAdapter(this, data)
        val manager = FlexboxLayoutManager()
        //设置主轴排列方式
        manager.flexDirection = FlexDirection.ROW
        //设置是否换行
        manager.flexWrap = FlexWrap.WRAP
        recyclerView.layoutManager = manager
        recyclerView.adapter = mAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter.notifyDataSetChanged()
        ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        ivSearch.setOnClickListener(View.OnClickListener {
            if (!TextUtils.isEmpty(etSearch.text)){
                intent = Intent(this, SearchListActivity::class.java)
                intent.putExtra("query", etSearch.text.toString())
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "请输入关键词", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun onItemClick(view: View?) {
        intent = Intent(this, SearchListActivity::class.java)
        intent.putExtra("query", data[recyclerView.getChildPosition(view!!)])
        startActivity(intent)
        finish()
    }

}