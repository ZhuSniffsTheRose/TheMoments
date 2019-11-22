package com.thoughtworks.themoments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoughtworks.themoments.adapters.MomentsAdapter
import com.thoughtworks.themoments.bean.*
import com.thoughtworks.themoments.network.ApiManger
import com.thoughtworks.themoments.network.MomentsApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mDisposableList = mutableListOf<Disposable>()

    private val mMomentsAdapter: MomentsAdapter by lazy {
        MomentsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        test.setOnClickListener {
            requestMoments()
        }
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
                .subscribe(onNext, onError)
        )
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
}
