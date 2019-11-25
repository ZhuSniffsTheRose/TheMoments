package com.thoughtworks.themoments

import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
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
 * Todo: 将非View层相关的代码整理至其他层， 比如数据请求及处理应在 Presenter 中.
 */
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {
        requestMoments()
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
            mMomentsAdapter.updateNewsList(formatData(t))
            Toast.makeText(this, t.size, Toast.LENGTH_LONG).show()
        }

        val onError = { exception: Throwable ->
            Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
        }

        mDisposableList.add(
            ApiManger.getApiService(MomentsApiInterface::class.java).fetchMomentsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showOrHideSwipeRefreshLayout(false) }
                .subscribe(onNext, onError)
        )
    }

    private fun showOrHideSwipeRefreshLayout(show: Boolean) {
        if (show) {
            requestMoments()
        }
        swpie_refresh_layout.isRefreshing = show
    }

    private fun formatData(t: MutableList<MomentsData>): MutableList<MomentsData> {
        val momentsDataFormatted = mutableListOf<MomentsData>()
        for (data in t) {
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
            data.viewType = viewType
            if (viewType != TYPE_MOMENTS_INVALID) {
                momentsDataFormatted.add(data)
            }

            if (!data.content.isNullOrEmpty()) {
                data.needShowAllSign = calculateShowCheckAllText(data.content)
            }
        }
        return momentsDataFormatted
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
