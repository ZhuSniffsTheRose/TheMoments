package com.thoughtworks.themoments

import android.util.TypedValue

/**
 * Created by Zhu on 2019-11-25
 */

fun dp2px(dpValue: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        TheMomentsApp.sContenxt!!.resources.displayMetrics
    ).toInt()
}


fun getScreenWidth(): Int {
    return TheMomentsApp.sContenxt!!.resources.displayMetrics.widthPixels
}
