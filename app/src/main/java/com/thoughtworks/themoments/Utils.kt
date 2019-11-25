package com.thoughtworks.themoments

import android.util.TypedValue

/**
 * Created by Zhu on 2019-11-25
 */

fun dp2px(resources: android.content.res.Resources, dpValue: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        resources.displayMetrics
    ).toInt()
}
