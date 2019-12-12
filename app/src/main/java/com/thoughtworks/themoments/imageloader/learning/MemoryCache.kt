package com.thoughtworks.themoments.imageloader.learning

import android.graphics.Bitmap
import androidx.collection.LruCache

/**
 * Created by Zhu on 2019-12-05
 */
class MemoryCache : ImageCache() {

    val mLruCache = LruCache<String, Bitmap>((Runtime.getRuntime().maxMemory() / 1024).toInt() / 4)

    override fun put(bitmap: Bitmap, url: String) {
        mLruCache.put(url, bitmap)
    }

    override fun get(url: String) = mLruCache.get(url)

}