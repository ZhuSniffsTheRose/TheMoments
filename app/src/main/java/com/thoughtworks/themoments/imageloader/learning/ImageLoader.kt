package com.thoughtworks.themoments.imageloader.learning

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Zhu on 2019-12-05
 */
class ImageLoader(val mConfig: ImageLoadeConfig) {


    var mExcutor: ExecutorService

    init {
        // 可以check 一下参数  比如 checkConfig
        checkConfig()
        mExcutor = Executors.newFixedThreadPool(mConfig.mThreadCount)
    }

    private fun checkConfig() {

    }

    fun displayImage(url: String, imageView: ImageView) {
        val bitmap = mConfig.mBitmapCache.get(url)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            return
        } else {
            submitLoadRequst(url, imageView)
        }

    }


    private fun submitLoadRequst(
        url: String,
        imageView: ImageView
    ) {
        imageView.setImageResource(mConfig.mDisplayConfig.mLoadingImageId)
        imageView.tag = url
        mExcutor.submit {
            val bitmap = dowloadBitmap(url)
            if (bitmap == null) {
                imageView.setImageResource(mConfig.mDisplayConfig.mLoadingFailedImageId)
                return@submit
            }

            if (imageView.tag == url) {
                imageView.setImageBitmap(bitmap)
            }
            mConfig.mBitmapCache.put(bitmap, url)
        }
    }


    fun dowloadBitmap(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        var `is`: InputStream? = null
        try {
            val url1 = URL(url)
            val conn = url1.openConnection() as HttpURLConnection

            conn.readTimeout = 1000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.doInput = true
            conn.connect()

            val response = conn.responseCode
            if (response == 200) {
                `is` = conn.inputStream
                bitmap = BitmapFactory.decodeStream(`is`)
            }
        } catch (e: ProtocolException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            `is`?.close()
        }
        return bitmap
    }
}