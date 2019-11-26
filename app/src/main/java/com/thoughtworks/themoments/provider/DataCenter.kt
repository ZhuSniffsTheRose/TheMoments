package com.thoughtworks.themoments.provider

import android.graphics.Paint
import com.thoughtworks.themoments.bean.*
import com.thoughtworks.themoments.dp2px
import com.thoughtworks.themoments.getScreenWidth

/**
 * Created by Zhu on 2019-11-26
 */
class DataCenter {

    var mMomentsData: MutableList<MomentsData>? = null

    fun handleData(data: MutableList<MomentsData>) {
        mMomentsData = formatData(data)
    }

    fun getDataHandled() = mMomentsData


    private fun formatData(t: MutableList<MomentsData>): MutableList<MomentsData> {
        val momentsDataFormatted = mutableListOf<MomentsData>()
        for (data in t) {
            data.viewType = getViewType(data)
            if (data.viewType != TYPE_MOMENTS_INVALID) {
                momentsDataFormatted.add(data)
            }

            if (!data.content.isNullOrEmpty()) {
                data.needShowAllSign = calculateShowCheckAllText(data.content)
            }
        }
        return momentsDataFormatted
    }

    private fun getViewType(data: MomentsData): Int {
        var viewType = TYPE_MOMENTS_INVALID
        val isContentExist = data.content?.isNotEmpty() ?: false
        val isImageExist = data.images?.isNotEmpty() ?: false

        when {
            isContentExist && !isImageExist -> viewType =
                TYPE_MOMENTS_CONTENT  // 仅展示文字
            !isContentExist && isImageExist -> viewType =
                TYPE_MOMENTS_PIC  // 仅展示图片
            isContentExist && isImageExist -> viewType =
                TYPE_MOMENTS_CONTENT_PIC // 文字和图片
        }
        return viewType
    }


    fun calculateShowCheckAllText(content: String?): Boolean {
        val textPaint = Paint()
        textPaint.textSize = dp2px(16f).toFloat()
        val textWidth = textPaint.measureText(content)
        val maxContentViewWidth =
            (getScreenWidth() - dp2px(74f)).toFloat()
        val maxLines = textWidth / maxContentViewWidth
        return maxLines > 4
    }

}