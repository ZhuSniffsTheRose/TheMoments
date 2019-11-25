package com.thoughtworks.themoments

import android.app.Application

/**
 * Created by Zhu on 2019-11-25
 */
class TheMomentsApp : Application() {

    companion object {
        var sContenxt: Application? = null
    }


    override fun onCreate() {
        super.onCreate()
        sContenxt = this
    }
}