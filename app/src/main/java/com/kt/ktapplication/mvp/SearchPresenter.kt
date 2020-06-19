package com.kt.ktapplication.mvp

import android.content.Context
import com.kt.ktapplication.mvp.contract.SearchResultContract
import com.kt.ktapplication.mvp.model.SearchModel
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by zz on 2020/6/16.
 */
class SearchPresenter(context: Context, view: SearchResultContract.View) :  SearchResultContract.Presenter{
    var mContext:Context = context
    var mView:SearchResultContract.View = view
    val mModel:SearchModel by lazy {
        SearchModel()
    }

    override fun requestData(query:String, page : Int) {
        val observable:Observable<HotBean> = mContext.let { mModel.loadData(mContext, query, page) }
        observable.applySchedulers().subscribe{
            mView.setData(it)
        }
    }

    override fun start() {
        TODO("Not yet implemented")
    }

}