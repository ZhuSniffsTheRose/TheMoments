package com.thoughtworks.themoments.imageloader.learning

import android.graphics.Bitmap

/**
 * Created by Zhu on 2019-12-05
 */
class DiskCache : ImageCache {
    override fun put(bitmap: Bitmap, url: String) {

    }

    override fun get(url: String): Bitmap? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}