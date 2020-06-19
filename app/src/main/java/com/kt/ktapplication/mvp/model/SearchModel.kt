package com.kt.ktapplication.mvp.model

import android.content.Context
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.network.ApiService
import com.kt.ktapplication.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by zz on 2020/6/16.
 */
class SearchModel {
    fun loadData(context: Context, query: String, page: Int) : Observable<HotBean>{
        val retrofitClient:RetrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)!!
        val apiService = retrofitClient.create(ApiService::class.java)
            return apiService!!.getSearchData(10, query, page)
        }
}