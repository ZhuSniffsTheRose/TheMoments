package com.thoughtworks.themoments.imageloader.learning

import android.graphics.Bitmap

/**
 * Created by Zhu on 2019-12-05
 */
abstract class ImageCache {
    abstract fun put(bitmap: Bitmap, url: String)

    abstract fun get(url: String): Bitmap?

}