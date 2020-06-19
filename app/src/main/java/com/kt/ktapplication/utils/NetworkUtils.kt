package com.kt.ktapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by zz on 2020/5/6.
 */
object NetworkUtils {
    fun isNetconnected(context: Context):Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectManager.activeNetworkInfo
        return if(networkInfo == null){
             false
        }else{
             networkInfo.isAvailable&& networkInfo.isConnected
        }
    }

    fun isNetworkConnected(context: Context, typeMobile : Int): Boolean{
        if(isNetconnected(context)){
            return false
        }
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectManager.getNetworkInfo(typeMobile)
        return if (networkInfo == null){
            false
        }else{
            networkInfo.isConnected && networkInfo.isAvailable
        }

    }
}