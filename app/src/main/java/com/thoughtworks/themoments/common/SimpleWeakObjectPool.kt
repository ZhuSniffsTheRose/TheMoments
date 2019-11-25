package com.orgzly.android

import java.lang.ref.WeakReference

/**
 * Created by Zhu on 2019-11-25
 */
class SimpleWeakObjectPool<T> @JvmOverloads constructor(size: Int = 5) {
    private val objsPool = arrayOfNulls<WeakReference<T>>(size)
    private var curPointer = -1
    @Synchronized
    fun get(): T? {
        if (curPointer == -1 || curPointer > objsPool.size) return null
        val obj = objsPool[curPointer]?.get()
        objsPool[curPointer] = null
        curPointer--
        return obj
    }

    @Synchronized
    fun put(t: T): Boolean {
        if (curPointer == -1 || curPointer < objsPool.size - 1) {
            curPointer++
            objsPool[curPointer] = WeakReference(t)
            return true
        }
        return false
    }

    fun clearPool() {
        for (i in objsPool.indices) {
            objsPool[i]?.clear()
            objsPool[i] = null
        }
        curPointer = -1
    }

    fun size(): Int {
        return objsPool.size
    }
}