package com.kt.ktapplication.network

import com.kt.ktapplication.mvp.model.bean.FindBean
import com.kt.ktapplication.mvp.model.bean.HomeBean
import com.kt.ktapplication.mvp.model.bean.HotBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by zz on 2020/5/6.
 */
interface ApiService {
    companion object{
        val BASE_URL: String get() = "http://baobab.kaiyanapp.com/api/"
    }

    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date: String, @Query("num") num: String) : Observable<HomeBean>

    @GET("v1/search")
    fun getSearchData(@Query("num") num: Int, @Query("query") query:String, @Query("start") start:Int):Observable<HotBean>

    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getFindData():Observable<MutableList<FindBean>>

    @GET("v3/ranklist")
    fun getRankData(@Query("num") num :Int,@Query("strategy") strategy :String,
                    @Query("udid") udid :String,@Query("vc") vc :Int):Observable<HotBean>
}