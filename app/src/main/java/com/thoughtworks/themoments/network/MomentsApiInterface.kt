package com.thoughtworks.themoments.network

import com.thoughtworks.themoments.bean.MomentsData
import com.thoughtworks.themoments.bean.UserInfoBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Zhu on 2019-11-22
 */
interface MomentsApiInterface {

    @GET("user/jsmith")
    fun fetchUserInfoBean(): Observable<UserInfoBean>

    @GET("user/jsmith/tweets")
    fun fetchMomentsBean(): Observable<MutableList<MomentsData>>
}