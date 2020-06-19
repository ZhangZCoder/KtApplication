package com.kt.ktapplication.network

import android.content.Context
import com.kt.ktapplication.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by zz on 2020/5/6.
 */
class CacheInterceptor(context: Context): Interceptor {
    val context = context
    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
        return if (NetworkUtils.isNetconnected(context)){
            val response = chain?.proceed(request)
            val maxAge: Int = 60
            val cacheControl = request?.cacheControl().toString()
            response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control", "public, max-age=" + maxAge)?.build()
        }else{
            request = request?.newBuilder()?.cacheControl(CacheControl.FORCE_CACHE)?.build()
            val response = chain?.proceed(request)
            val maxStale = 60 * 60 * 24 * 3
            response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control","public, only-if-cached, max-stale=" + maxStale)?.build()
        }
    }
}