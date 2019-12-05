package com.thoughtworks.themoments.imageloader.learning

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream

/**
 * Created by Zhu on 2019-12-05
 *
 *  内存缓存虽然解决了每次从网络加载图片的问题，
 *  但是 Android 应用的内存有限，且具有易失性，即当应用重新启动，原来已经加载过的图片将会丢失
 *  所以引入 本地缓存
 */
class DiskCache : ImageCache {

    companion object {
        const val cacheDir = "sdcard/cache/"
    }

    override fun put(bitmap: Bitmap, url: String) {
        var fos: FileOutputStream
        fos = FileOutputStream(cacheDir + url)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.close()
    }

    override fun get(url: String) = BitmapFactory.decodeFile(cacheDir + url)
}