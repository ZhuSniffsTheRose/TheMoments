package com.thoughtworks.themoments

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

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


fun loadOnlineImg(
    context: Context,
    url: String,
    imageView: ImageView
) {
    val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground).fitCenter()
    Glide.with(context).load(url).apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)

}