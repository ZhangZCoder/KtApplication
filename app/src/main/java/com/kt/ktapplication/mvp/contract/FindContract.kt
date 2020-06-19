package com.kt.ktapplication.mvp.contract

import com.kt.ktapplication.base.BasePresenter
import com.kt.ktapplication.base.BaseView
import com.kt.ktapplication.mvp.model.bean.FindBean

/**
 * Created by zz on 2020/6/18.
 */
interface FindContract {
    interface View:BaseView<Presenter>{
        fun setData(findBean: MutableList<FindBean>)
    }

    interface Presenter:BasePresenter{
        fun requestData()
    }
}