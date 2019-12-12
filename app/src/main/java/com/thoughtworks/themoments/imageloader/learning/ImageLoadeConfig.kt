package com.thoughtworks.themoments.imageloader.learning

import kotlin.math.max

/**
 * Created by Zhu on 2019-12-12
 */
class ImageLoadeConfig private constructor() {

    var mBitmapCache: ImageCache = MemoryCache()
    var mDisplayConfig = DisplayConfig()
    var mThreadCount = Runtime.getRuntime().availableProcessors()


    class Builder {
        var mBitmapCache: ImageCache = MemoryCache()
        var mDiskCache = DisplayConfig()
        var mThreadCount = Runtime.getRuntime().availableProcessors()

        fun setThreadCount(count: Int): Builder {
            mThreadCount = max(1, count)
            return this
        }

        fun setLoadingImage(imageId: Int): Builder {
            mDiskCache.mLoadingImageId = imageId
            return this
        }

        fun setLoadingFailedImage(imageId: Int): Builder {
            mDiskCache.mLoadingFailedImageId = imageId
            return this
        }

        /**
         * 将设置给 builder 的值 传递给 config
         */
        fun applyConfig(config: ImageLoadeConfig) {
            config.mBitmapCache = mBitmapCache
            config.mDisplayConfig = mDiskCache
            config.mThreadCount = mThreadCount
        }

        /**
         * 创建 config, 将 config 对象给 builder , 让其把用户设置的值传递到 config 中
         * 然后将 config 传递到 ImageLoader
         */
        fun create(): ImageLoadeConfig {
            val config = ImageLoadeConfig()
            applyConfig(config)
            return config
        }
    }


    class DisplayConfig {
        var mLoadingImageId = 0
        var mLoadingFailedImageId = 0

    }
}