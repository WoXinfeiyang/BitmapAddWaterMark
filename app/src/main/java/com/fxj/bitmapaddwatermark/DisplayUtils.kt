package com.fxj.bitmapaddwatermark

import android.content.Context

class DisplayUtils {

    companion object {
        /**
         * dp转px
         * @param context
         * @param dipValue--dip值
         * */
        fun dipToPx(context: Context,dipValue:Float):Float{
            val density= context.resources.displayMetrics.density;
            return dipValue*density;
        }
    }
}