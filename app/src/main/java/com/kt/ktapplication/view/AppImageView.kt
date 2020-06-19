package com.kt.ktapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.BLACK
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by zz on 2020/5/12.
 */
@SuppressLint("AppCompatCustomView")
class AppImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    //public static final
    val TAG = "SelectableRoundedImageView"
    private val mResource = 0
    private val sScaleTypeArray : Array<ScaleType> = arrayOf(
        ScaleType.MATRIX,
        ScaleType.FIT_XY,
        ScaleType.FIT_START,
        ScaleType.FIT_CENTER,
        ScaleType.FIT_END,
        ScaleType.CENTER,
        ScaleType.CENTER_CROP,
        ScaleType.CENTER_INSIDE
    )
    //default
    private var mScaleType = ScaleType.FIT_XY
    private var mLeftTopCornerRadius = 0.0f
    private var mRightTopCornerRadius = 0.0f
    private var mLeftBottomCornerRadius = 0.0f
    private var mRightBottomCornerRadius = 0.0f

    private var mBorderWidth = 0.0f
    private val DEFAULT_BORDER_COLOR: Int = Color.BLACK
    private var mBorderColor: ColorStateList = ColorStateList.valueOf(DEFAULT_BORDER_COLOR)



    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}