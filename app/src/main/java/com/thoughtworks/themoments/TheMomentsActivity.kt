package com.thoughtworks.themoments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.themoments.adapters.MomentsAdapter
import com.thoughtworks.themoments.bean.MomentsData
import com.thoughtworks.themoments.bean.UserInfoBean
import com.thoughtworks.themoments.network.ApiManger
import com.thoughtworks.themoments.network.MomentsApiInterface
import com.thoughtworks.themoments.provider.DataCenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 考虑本 HomeWork 功能和界面较为单一，就未采用当前主流的 mvp or mvvm 等架构，避免过度设计.
 */
class TheMomentsActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val TAG = TheMomentsActivity::class.java.simpleName

    override fun onRefresh() {
        requestUserInfo()
    }

    private val mDisposableList = mutableListOf<Disposable>()

    private val mDataCenter = DataCenter()

    private val mMomentsAdapter: MomentsAdapter by lazy {
        MomentsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        showOrHideSwipeRefreshLayout(true)
        swpie_refresh_layout.setOnRefreshListener(this)
    }


    private fun requestMoments() {

        val onNext: (t: MutableList<MomentsData>) -> Unit = {
            mDataCenter.handleData(it)
            val data = mDataCenter.getDataHandled()
            if (data == null) {
                Log.w(TAG, "DataCenter provide null-data")
            } else {
                mMomentsAdapter.updateNewsList(data)
            }
        }

        val onError: (exception: Throwable) -> Unit = {
            Log.e(TAG, "response of Moments onError ===> ${it.message}", it)
        }

        mDisposableList.add(
            ApiManger.getApiService(MomentsApiInterface::class.java).fetchMomentsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showOrHideSwipeRefreshLayout(false) }
                .subscribe(onNext, onError)
        )
    }

    private fun requestUserInfo() {
        val onNext = { t: UserInfoBean ->
            mMomentsAdapter.setHeaderUserInfo(t)
            requestMoments()
        }

        val onError: (exception: Throwable) -> Unit = {
            Log.e(TAG, "response of UserInfo onError ===> ${it.message}", it)
        }

        mDisposableList.add(
            ApiManger.getApiService(MomentsApiInterface::class.java).fetchUserInfoBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError)
        )
    }


    private fun showOrHideSwipeRefreshLayout(show: Boolean) {
        if (show) {
            requestUserInfo()
        }
        swpie_refresh_layout.isRefreshing = show
    }


    private fun initRv() {
        moments_recycler_view.layoutManager = LinearLayoutManager(this)
        moments_recycler_view.adapter = mMomentsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposableList.forEach { action -> action.dispose() }
    }
}
