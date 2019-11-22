package com.thoughtworks.themoments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoughtworks.themoments.adapters.MomentsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mMomentsAdapter: MomentsAdapter by lazy {
        MomentsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        requestMoments()
    }

    private fun requestMoments() {
        val list = mutableListOf<String>()
        for (i in 1..100) {
            list.add(i.toString())
        }
        mMomentsAdapter.setNewData(list)
    }

    private fun initRv() {
        moments_recycler_view.layoutManager = LinearLayoutManager(this)
        moments_recycler_view.adapter = mMomentsAdapter
    }
}
