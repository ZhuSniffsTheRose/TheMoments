package com.thoughtworks.themoments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoughtworks.themoments.adapters.MomentsAdapter
import com.thoughtworks.themoments.bean.MomentsData
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
            mMomentsAdapter.updateNewsList(t)
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

    private fun initRv() {
        moments_recycler_view.layoutManager = LinearLayoutManager(this)
        moments_recycler_view.adapter = mMomentsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposableList.forEach { action -> action.dispose() }
    }
}
