package com.kt.ktapplication.mvp

import android.content.Context
import android.database.Observable
import com.kt.ktapplication.mvp.contract.RankContract
import com.kt.ktapplication.mvp.model.RankModel
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.utils.applySchedulers

/**
 * Created by zz on 2020/6/19.
 */
class RankPresenter(context: Context, view:RankContract.View):RankContract.Presenter {
    var mContext:Context = context
    var rankModel:RankModel = RankModel()
    var view:RankContract.View = view

    override fun requestData(strategy:String) {
        val observable:io.reactivex.Observable<HotBean> = mContext.let { rankModel.loadData(mContext, strategy) }
        observable.applySchedulers().subscribe {
            view.setData(it)
        }
    }

    override fun start() {
    }
}