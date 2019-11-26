package com.thoughtworks.themoments.bean
import com.google.gson.annotations.SerializedName


/**
 * Created by Zhu on 2019-11-26
 */
data class UserInfoBean(
    val avatar: String,
    val nick: String,
    @SerializedName("profile-image")
    val profileImage: String,
    val username: String
)