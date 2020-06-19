package com.kt.ktapplication.mvp.model

import android.content.Context
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.network.ApiService
import com.kt.ktapplication.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by zz on 2020/6/19.
 */
class RankModel  {
    fun loadData(context: Context, strategy:String):Observable<HotBean>{
        val retrofitClient:RetrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)!!
        val apiService:ApiService? = retrofitClient.create(ApiService::class.java)
        return apiService!!.getRankData(10, strategy!!,"26868b32e808498db32fd51fb422d00175e179df",83)
    }
}