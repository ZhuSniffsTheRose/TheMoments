package com.thoughtworks.themoments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.themoments.R
import com.thoughtworks.themoments.bean.MomentsData
import kotlinx.android.synthetic.main.item_recycler_moments_word.view.*

/**
 * Created by Zhu on 2019-11-22
 */
class MomentsAdapter(var moments: MutableList<MomentsData> = arrayListOf()) :
    RecyclerView.Adapter<BaseHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) =
        BaseHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_recycler_moments_word
                , container, false
            )
        )

    override fun getItemCount() = moments.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.itemView.item_name.text = moments[position].toString().subSequence(0, 3)
    }


    fun updateNewsList(moments: MutableList<MomentsData>) {
        this.moments = moments
        notifyItemRangeChanged(0, moments.size)
    }
}

class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView)