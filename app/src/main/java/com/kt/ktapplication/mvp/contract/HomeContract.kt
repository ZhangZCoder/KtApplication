package com.kt.ktapplication.mvp.contract

import com.kt.ktapplication.base.BasePresenter
import com.kt.ktapplication.base.BaseView
import com.kt.ktapplication.mvp.model.bean.HomeBean

/**
 * Created by zz on 2020/4/30.
 */
interface HomeContract  {
    interface View : BaseView<Presenter>{
        fun setData(bean : HomeBean)
    }
    interface Presenter : BasePresenter{
        fun requestData()
    }
}