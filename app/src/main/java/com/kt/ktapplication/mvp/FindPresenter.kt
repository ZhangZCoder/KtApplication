package com.kt.ktapplication.mvp

import android.content.Context
import com.kt.ktapplication.mvp.contract.FindContract
import com.kt.ktapplication.mvp.model.FindModel
import com.kt.ktapplication.mvp.model.bean.FindBean
import com.kt.ktapplication.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by zz on 2020/6/18.
 */
class FindPresenter(context: Context, view:FindContract.View):FindContract.Presenter {
    val findModel: FindModel =
        FindModel()
    var mContext:Context = context
    var mView = view
    init {

    }
    override fun requestData() {
        val observable:Observable<MutableList<FindBean>> = mContext.let { findModel.loadData(mContext) }
        observable.applySchedulers().subscribe{
            beans:MutableList<FindBean> ->
            mView.setData(beans)
        }
    }

    override fun start() {
        requestData()
    }
}