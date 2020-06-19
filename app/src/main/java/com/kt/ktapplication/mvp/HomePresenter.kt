package com.kt.ktapplication.mvp

import android.content.Context
import android.util.Log
import com.kt.ktapplication.mvp.contract.HomeContract
import com.kt.ktapplication.mvp.model.HomeModel
import com.kt.ktapplication.mvp.model.bean.HomeBean
import com.kt.ktapplication.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by zz on 2020/5/6.
 */
class HomePresenter(context : Context, view : HomeContract.View) : HomeContract.Presenter {
    var mContext : Context? = null
    var mView : HomeContract.View? = null
    val mModel : HomeModel by lazy {
        HomeModel()
    }
    init {
        mView = view
        mContext = context
    }

    override fun requestData() {
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it, true, "0") }
        observable?.applySchedulers()?.subscribe{
                homeBean : HomeBean ->
            mView?.setData(homeBean)
            Log.d("=======first", homeBean.toString())
        }
    }

    override fun start() {
        requestData()
    }

    fun moreData(data : String) {
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it, false, data) }
            observable?.applySchedulers()?.subscribe{
                homeBean : HomeBean ->
                mView?.setData(homeBean)
                Log.d("=======more", homeBean.toString())
            }
    }
}