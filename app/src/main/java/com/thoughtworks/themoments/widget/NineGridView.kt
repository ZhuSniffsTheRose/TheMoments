package com.thoughtworks.themoments.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.orgzly.android.SimpleWeakObjectPool

/**
 * Created by Zhu on 2019-11-25
 */
class NineGridView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ViewGroup(context, attrs), ViewGroup.OnHierarchyChangeListener {
    private var mAdapter: NineGridAdapter? = null

    private var mRows: Int = 0
    private var mColumns: Int = 0
    var mSpace: Int = 0
    var mChildWidth: Int = 0
    var childHeight: Int = 0
    private val IMAGE_POOL: SimpleWeakObjectPool<View> by lazy { SimpleWeakObjectPool<View>(5) }
    private var mSingleWidth: Int = 0
    private var mSingleHeight: Int = 0

    private var mImageViews = mutableListOf<ImageView>()

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        setOnHierarchyChangeListener(this)
        mSpace = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            4f, context.resources.displayMetrics
        ).toInt()
    }

    fun setAdapter(adapter: NineGridAdapter) {
        mImageViews.clear()
        mAdapter = adapter
        val oldCount = childCount
        val newCount = adapter.getCount()
        initMatrix(newCount)
        removeScrapViews(oldCount, newCount)
        addChildrenData(adapter)
        requestLayout()
    }


    private fun initMatrix(length: Int) {
        if (length <= 3) {
            mRows = 1
            mColumns = length
        } else if (length <= 6) {
            mRows = 2
            mColumns = 3 // 因为length <=6 所以实际Columns<3也是不会导致计算出问题的
            if (length == 4) {
                mColumns = 2
            }
        } else {
            mRows = 3
            mColumns = 3
        }
    }

    private fun removeScrapViews(oldCount: Int, newCount: Int) {
        if (newCount < oldCount) {
            removeViewsInLayout(newCount, oldCount - newCount)
        }
    }

    private fun addChildrenData(adapter: NineGridAdapter) {
        val childCount = childCount
        val count = adapter.getCount()
        for (i in 0 until count) {
            val hasChild = i < childCount
            // 简单的回收机制,主要是为ListView/RecyclerView做优化
            var recycleView: View? = if (hasChild) getChildAt(i) else null
            if (recycleView == null) {
                recycleView = IMAGE_POOL.get()
                val child = adapter.getView(i, recycleView)
                addViewInLayout(child, i, child.layoutParams, true)
                mImageViews.add(child as ImageView)
            } else {
                adapter.getView(i, recycleView)
                mImageViews.add(recycleView as ImageView)
            }
        }
    }

    override fun addViewInLayout(
        child: View,
        index: Int,
        params: LayoutParams,
        preventRequestLayout: Boolean
    ): Boolean {
        if (child !is ImageView) {
            throw ClassCastException("only imageView can be add")
        }
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childCount = childCount
        if (childCount <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }

        val minW = paddingLeft + paddingRight + suggestedMinimumWidth
        val width = View.resolveSizeAndState(minW, widthMeasureSpec, 1)
        val availableWidth = width - paddingLeft - paddingRight
        if (childCount <= 1) {
            mChildWidth = if (mSingleWidth == 0) {
                availableWidth * 2 / 5
            } else {
                availableWidth / 2
            }
            childHeight = if (mSingleHeight == 0) {
                mChildWidth
            } else {
                (mSingleHeight / mSingleWidth.toFloat() * mChildWidth).toInt()
            }
        } else {
            mChildWidth = (availableWidth - mSpace * (mColumns - 1)) / 3
            childHeight = mChildWidth
        }
        val height = childHeight * mRows + mSpace * (mRows - 1)
        setMeasuredDimension(width, height + paddingTop + paddingBottom)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layoutChildren()
    }

    private fun layoutChildren() {
        if (mRows <= 0 || mColumns <= 0) {
            return
        }

        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i) as ImageView

            val row = i / mColumns
            val col = i % mColumns
            val left = (mChildWidth + mSpace) * col + paddingLeft
            val top = (childHeight + mSpace) * row + paddingTop
            val right = left + mChildWidth
            val bottom = top + childHeight
            view.layout(left, top, right, bottom)
        }
    }


    override fun onChildViewAdded(parent: View, child: View) {

    }

    override fun onChildViewRemoved(parent: View, child: View) {
        IMAGE_POOL.put(child)
    }


    interface NineGridAdapter {
        fun getCount(): Int

        fun getItem(position: Int): String

        fun getView(position: Int, itemView: View?): View
    }
}