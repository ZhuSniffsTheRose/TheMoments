package com.thoughtworks.themoments.bean

import com.google.gson.annotations.SerializedName


/**
 * Created by Zhu on 2019-11-22
 */

const val TYPE_MOMENTS_INVALID = 0xFF00
const val TYPE_MOMENTS_CONTENT_PIC = 0xFF01
const val TYPE_MOMENTS_CONTENT = 0xFF02
const val TYPE_MOMENTS_PIC = 0xFF03
const val TYPE_MOMENTS_HEADER = 0xFF04

data class MomentsData(
    val comments: MutableList<CommentBean>?,
    val content: String?,
    val error: String?,
    val images: MutableList<ImageBean>?,
    val sender: SenderBean?,
    @SerializedName("unknown error")
    val unknownError: String?,

    var viewType: Int,
    var isExpand: Boolean = false,
    var needShowAllSign: Boolean = false
)

data class CommentBean(
    val content: String,
    val sender: SenderBean
)

data class SenderBean(
    val avatar: String,
    val nick: String,
    val username: String
)

data class ImageBean(
    val url: String
)
