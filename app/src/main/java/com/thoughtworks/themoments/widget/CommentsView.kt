package com.thoughtworks.themoments.widget

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.thoughtworks.themoments.bean.CommentBean


/**
 * Created by Zhu on 2019-11-25
 */
class CommentsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, 0) {
    val TAG = CommentsView::class.java.simpleName

    lateinit var mMutableList: MutableList<CommentBean>;

    init {
        orientation = VERTICAL
        setBackgroundColor(Color.parseColor("#F2F2F2"))
    }


    fun setComments(comments: MutableList<CommentBean>?) {
        if (comments.isNullOrEmpty()) {
            Log.w(TAG, "comments.isNullOrEmpty()")
            return
        }
        mMutableList = comments
        notifyDataSetChanged()
    }


    fun notifyDataSetChanged() {
        removeAllViews()
        if (mMutableList.isNullOrEmpty()) {
            return
        }

        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(5, 5, 0, 5)
        for (i in 0 until mMutableList.size) {
            val view = getView(i)
            addView(view, i, layoutParams)
        }
    }

    private fun getView(position: Int): View {
        val item = mMutableList[position]
        val textView = TextView(context)
        textView.textSize = 15f
        textView.setTextColor(Color.parseColor("#686868"))
        val nick = item.sender.nick
        textView.text = Html.fromHtml("<font color=#387dcc>$nick :</font>" + item.content)
        return textView
    }
}