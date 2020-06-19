package com.kt.ktapplication.mvp.contract

import com.kt.ktapplication.base.BasePresenter
import com.kt.ktapplication.base.BaseView
import com.kt.ktapplication.mvp.model.bean.HotBean

/**
 * Created by zz on 2020/6/16.
 */
interface SearchResultContract {

    interface View:BaseView<Presenter>{
        fun setData(bean:HotBean)
    }

    interface Presenter:BasePresenter{
        fun requestData(query:String, page : Int)
    }
}