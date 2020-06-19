package com.kt.ktapplication.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2020/4/28.
 */
public class FontUtils {

    private static final Map<String, Typeface> fontMap = new HashMap();
    private static Typeface getFontTypefaceFromAssert(String fontName, Context context) {
        try {
            synchronized (fontMap) {
                if (!fontMap.containsKey(fontName)) {
                    Typeface typface = Typeface.createFromAsset(context.getAssets(), fontName);
                    fontMap.put(fontName, typface);
                }
                Typeface typface = fontMap.get(fontName);
                return typface;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Typeface getFontTypefaceByName(String fontName, Context context) {
        return getFontTypefaceFromAssert(fontName,context);
    }

    public static Typeface getFZ_DB1(Context context) {
        return getFontTypefaceFromAssert("fonts/FZLanTingHeiS-DB1-GB-Regular.TTF", context);
    }

    public static Typeface getFZ_L_GB(Context context) {
        return getFontTypefaceFromAssert("fonts/FZLanTingHeiS-L-GB-Regular.TTF",context);
    }

    public static Typeface getLobster(Context context) {
        return getFontTypefaceFromAssert("fonts/Lobster-1.4.otf",context);
    }

    static public void changeFont(View element, Typeface tf){
        if (element instanceof TextView) {
            ((TextView) element).setTypeface(tf);
        }else if(element instanceof EditText){
            ((EditText)element).setTypeface(tf);
        }
    }
}
