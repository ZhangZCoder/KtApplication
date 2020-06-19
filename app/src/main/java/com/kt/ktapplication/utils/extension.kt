package com.kt.ktapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * Created by zz on 2020/4/27.
 */
fun Context.showToast(msg:String) : Toast{
    var toast:Toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

inline fun<reified T:Activity> Activity.newIntent(){
    val intent:Intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun<T> io.reactivex.Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).
           unsubscribeOn(Schedulers.io()).
           observeOn(AndroidSchedulers.mainThread())

}

