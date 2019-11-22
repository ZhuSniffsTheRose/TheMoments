package com.thoughtworks.themoments.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by Zhu on 2019-11-22
 */
class MomentsAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.activity_list_item) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(android.R.id.text1, item)
    }
}