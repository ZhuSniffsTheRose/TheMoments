package com.thoughtworks.themoments

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.themoments.adapters.MomentsAdapter
import com.thoughtworks.themoments.bean.*
import com.thoughtworks.themoments.network.ApiManger
import com.thoughtworks.themoments.network.MomentsApiInterface
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
        val onNext = { t: MutableList<MomentsData> ->
            moments_recycler_view.adapter = mMomentsAdapter
            mMomentsAdapter.updateNewsList(formatData(t))
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

    private fun formatData(t: MutableList<MomentsData>): MutableList<MomentsData> {
        val momentsDataFormatted = mutableListOf<MomentsData>()
        for (data in t) {
            data.viewType = getViewType(data)
            if (data.viewType  != TYPE_MOMENTS_INVALID) {
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

    private fun initRv() {
        moments_recycler_view.layoutManager = LinearLayoutManager(this)
        moments_recycler_view.adapter = mMomentsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposableList.forEach { action -> action.dispose() }
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
