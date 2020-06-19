package com.kt.ktapplication.mvp.model

import android.content.Context
import android.widget.Toast
import com.kt.ktapplication.mvp.model.bean.HomeBean
import com.kt.ktapplication.network.ApiService
import com.kt.ktapplication.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by zz on 2020/5/6.
 */
class HomeModel {
    fun loadData(context: Context,isFirst: Boolean,data: String?): Observable<HomeBean>?{
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient?.create(ApiService::class.java)
         when(isFirst){
            true ->
            {
                return apiService?.getHomeData()
            }
            false -> {
                return apiService?.getHomeMoreData(data.toString(), "2")
            }
        }
    }
}