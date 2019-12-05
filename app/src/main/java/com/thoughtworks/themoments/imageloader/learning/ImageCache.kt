package com.thoughtworks.themoments.imageloader.learning

import android.graphics.Bitmap

/**
 * Created by Zhu on 2019-12-05
 */
interface ImageCache {
    fun put(bitmap: Bitmap, url: String)

    fun get(url: String) : Bitmap?
}