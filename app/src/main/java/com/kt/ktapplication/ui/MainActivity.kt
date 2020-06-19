package com.kt.ktapplication.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.kt.ktapplication.R
import com.kt.ktapplication.ui.search.activity.SearchActivity
import com.kt.ktapplication.ui.fragment.FindFragment
import com.kt.ktapplication.ui.fragment.HomeFragment
import com.kt.ktapplication.ui.fragment.HotFragment
import com.kt.ktapplication.ui.fragment.MyFragment
import com.kt.ktapplication.utils.newIntent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
/**
 * Created by zz on 2020/4/29.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    var homeFragment: HomeFragment? = null
    var findFragment: FindFragment? = null
    var hotFragment: HotFragment? = null
    var myFragment: MyFragment? = null
    var context:Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar().barAlpha(0.2f).fitsSystemWindows(true).init()
        val window = window
        val params = window.attributes
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params
        setRadioButton()
        initToolbar()
        initFragment(savedInstanceState)
    }
    private fun setRadioButton(){
        rb_home.isChecked = true
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_mine.setOnClickListener(this)
    }

    private fun initToolbar(){
        var today = getToday()
        tv_bar_title.text = today
    }

    private fun initFragment(savedInstanceState : Bundle?){
        homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_content, homeFragment!!, null).commit()
        findFragment = FindFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_content, findFragment!!, null).commit()
        hotFragment = HotFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_content, hotFragment!!, null).commit()
        myFragment = MyFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_content, myFragment!!, null).commit()
        homeFragment?.let { myFragment?.let { it1 ->
            findFragment?.let { it2 ->
                hotFragment?.let { it3 ->
                    supportFragmentManager.beginTransaction().show(it).hide(it2).hide(it3).hide(
                        it1
                    ).commit()
                }
            }
        } }
        iv_search.setOnClickListener(this)
    }

    private fun getToday():String{
        var list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        var calendar:Calendar = Calendar.getInstance()
        var index:Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0){
            index = 0
        }

        return list[index]
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.rb_home ->{
                rb_home.isChecked = true
                tv_bar_title.text = getToday()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
                homeFragment?.let { myFragment?.let { it1 ->
                    findFragment?.let { it2 ->
                        hotFragment?.let { it3 ->
                            supportFragmentManager.beginTransaction().show(it).hide(it2).hide(it3).hide(
                                it1
                            ).commit()
                        }
                    }
                } }
            }
            R.id.rb_find ->{
                rb_find.isChecked = true
                tv_bar_title.text = "Discover"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
                findFragment?.let { homeFragment?.let { it1 ->
                    hotFragment?.let { it2 ->
                        myFragment?.let { it3 ->
                            supportFragmentManager.beginTransaction().show(it).hide(
                                it1
                            ).hide(it2).hide(it3).commit()
                        }
                    }
                } }
            }
            R.id.rb_hot ->{
                rb_hot.isChecked = true
                tv_bar_title.text = "Ranking"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
                hotFragment?.let { homeFragment?.let { it1 ->
                    findFragment?.let { it2 ->
                        myFragment?.let { it3 ->
                            supportFragmentManager.beginTransaction().show(it).hide(
                                it1
                            ).hide(it2).hide(it3).commit()
                        }
                    }
                } }
            }
            R.id.rb_mine ->{
                rb_mine.isChecked = true
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setImageResource(R.drawable.icon_setting)
                myFragment?.let { homeFragment?.let { it1 ->
                    findFragment?.let { it2 ->
                        hotFragment?.let { it3 ->
                            supportFragmentManager.beginTransaction().show(it).hide(
                                it1
                            ).hide(it2).hide(it3).commit()
                        }
                    }
                } }

            }
            R.id.iv_search ->{
                if (rb_mine.isChecked){

                }else{
                    intent = Intent(context, SearchActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }
}

