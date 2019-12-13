package com.thoughtworks.themoments.imageloader.learning

import com.thoughtworks.themoments.R
import com.thoughtworks.themoments.imageloader.CacheFactor

/**
 * Created by Zhu on 2019-12-12
 *
 * 链式调用的效果,  调用方法会返回当前调用对象， 这样就 助解 了 Rx 的链式调用.
 */
class LoaderTest {

    fun test() {


        var config = ImageLoadeConfig.Builder()
            .setLoadingFailedImage(R.drawable.ic_launcher_foreground)
            .setLoadingImage(R.drawable.ic_launcher_background)
            .setThreadCount(3)
            .setCache(CacheFactor.getCache(MemoryCache::class.java))
            .create()

        val imageLoader = ImageLoader(config)


    }
}