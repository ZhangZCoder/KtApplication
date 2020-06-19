package com.kt.ktapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import com.kt.ktapplication.R
import com.kt.ktapplication.utils.newIntent
import kotlinx.android.synthetic.main.activity_splash.*
/**
 * Created by zz on 2020/4/29.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        setAnimation()
    }
    private fun setAnimation(){
        val alphaAnimation = AlphaAnimation(0.1f, 1f)
        alphaAnimation.duration = 1000
        val scaleAnimation = ScaleAnimation(0.1f,1.0f,0.1f,1.0f,
            ScaleAnimation.RELATIVE_TO_SELF,0.5f,
            ScaleAnimation.RELATIVE_TO_SELF,0.5f)
        scaleAnimation.duration = 1000
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(alphaAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.duration = 1000
        iv_icon_splash.startAnimation(animationSet)
        animationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                newIntent<MainActivity>()
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
    }
}
