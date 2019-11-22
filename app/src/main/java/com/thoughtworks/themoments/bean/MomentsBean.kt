package com.thoughtworks.themoments.bean

import com.google.gson.annotations.SerializedName


/**
 * Created by Zhu on 2019-11-22
 */
data class MomentsData(
    val comments: List<Any>,
    val content: String,
    val error: String,
    val images: List<ImageBean>,
    val sender: SenderBean,
    @SerializedName("unknown error")
    val unknownError: String
)

data class SenderBean(
    val avatar: String,
    val nick: String,
    val username: String
)

data class ImageBean(
    val url: String
)