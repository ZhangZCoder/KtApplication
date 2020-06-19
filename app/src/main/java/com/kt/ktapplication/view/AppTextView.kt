package com.kt.ktapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.kt.ktapplication.R
import com.kt.ktapplication.utils.FontUtils

/**
 * Created by zz on 2020/4/28.
 */
@SuppressLint("AppCompatCustomView")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AppTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0):
    TextView(context, attrs, defStyleAttr) {
    object TypefaceValue{
        const val fz_db1_gb = 0
        const val fz_l_gb = 1
        const val lobster = 2
    }

    init {
        var typeface:Typeface? = null
        if (!isInEditMode) {
            if (null != attrs) {
                var a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
                typeface = typefaceFromAttrs(context, a)
                a.recycle()
            }
            if (typeface == null)
                typeface = Typeface.DEFAULT
        }
        setTypeface(typeface)
    }

    private fun typefaceFromAttrs(context: Context?, a: TypedArray): Typeface {
        if (a.hasValue(R.styleable.CustomTextView_typeface)) {
            var typefaceValue: Int = a.getInt(R.styleable.CustomTextView_typeface, 0)
            typeface = when (typefaceValue) {
                TypefaceValue.fz_l_gb -> FontUtils.getFZ_L_GB(context)
                TypefaceValue.fz_db1_gb -> FontUtils.getFZ_DB1(context)
                TypefaceValue.lobster -> FontUtils.getLobster(context)
                else -> Typeface.DEFAULT
            }
        }
        if (null == typeface){
            typeface = Typeface.DEFAULT
        }
        return typeface
    }
}