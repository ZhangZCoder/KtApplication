package com.kt.ktapplication.mvp.model

import android.content.Context
import com.kt.ktapplication.mvp.model.bean.FindBean
import com.kt.ktapplication.network.ApiService
import com.kt.ktapplication.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by zz on 2020/6/18.
 */
class FindModel() {
    fun loadData(context: Context):Observable<MutableList<FindBean>>{
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient?.create(ApiService::class.java)
        return apiService!!.getFindData()
    }
}