package com.thoughtworks.themoments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thoughtworks.themoments.R
import com.thoughtworks.themoments.bean.MomentsData
import com.thoughtworks.themoments.bean.TYPE_MOMENTS_CONTENT
import com.thoughtworks.themoments.bean.TYPE_MOMENTS_CONTENT_PIC
import com.thoughtworks.themoments.bean.TYPE_MOMENTS_PIC
import kotlinx.android.synthetic.main.include_item_recycler_content.view.*
import kotlinx.android.synthetic.main.item_recycler_moments_word.view.*

/**
 * Created by Zhu on 2019-11-22
 */
class MomentsAdapter(var moments: MutableList<MomentsData> = arrayListOf()) :
    RecyclerView.Adapter<BaseHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) =
        BaseHolder(
            LayoutInflater.from(container.context).inflate(
                when (viewType) {
                    TYPE_MOMENTS_CONTENT_PIC -> R.layout.item_recycler_moments_word
                    TYPE_MOMENTS_CONTENT -> R.layout.item_recycler_moments_word
                    TYPE_MOMENTS_PIC -> R.layout.item_recycler_moments_word
                    else -> R.layout.item_recycler_moments_word
                }, container, false
            )
        )

    override fun getItemCount() = moments.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val itemType = getItemViewType(position)
        val momentsData = moments[position]

        setCommonInfo(holder.itemView, momentsData)

        when (itemType) {
            TYPE_MOMENTS_CONTENT_PIC -> holder.itemView.let {
                //                it.txt_user_name.text = moments[position].toString().subSequence(0, 3)
            }


            TYPE_MOMENTS_CONTENT -> holder.itemView.let {
                it.txt_user_name.text = momentsData.sender!!.nick
                it.txt_content.text = momentsData.content
                Glide.with(it.context)
                    .asDrawable().load(momentsData.sender!!.avatar)
                    .apply(
                        RequestOptions.centerCropTransform()
                            .skipMemoryCache(true)
                            .placeholder(R.drawable.ic_launcher_background)
                    )
                    .into(it.img_avatar)
            }


            TYPE_MOMENTS_PIC -> holder.itemView.let {
                //                it.item_name.text = moments[position].toString().subSequence(0, 3)
            }

            else -> holder.itemView.let {
                //                it.item_name.text = moments[position].toString().subSequence(0, 3)
            }

        }

    }

    private fun setCommonInfo(itemView: View, momentsData: MomentsData) {
        setContentInfo(
            itemView,
            momentsData.content,
            momentsData.needShowAllSign,
            momentsData.isExpand
        )
        itemView.txt_state.setOnClickListener {
            momentsData.isExpand = !momentsData.isExpand
            setTextState(itemView, momentsData.isExpand)
        }


        itemView.comments_view.setComments(momentsData.comments)

    }

    private fun setContentInfo(
        itemView: View,
        content: String?,
        needShowAllSign: Boolean,
        isExpand: Boolean
    ) {
        itemView.txt_content.text = content
        itemView.txt_state.visibility = if (needShowAllSign) {
            View.VISIBLE
        } else {
            View.GONE
        }
        if (needShowAllSign) {
            setTextState(itemView, isExpand)
        }
    }

    private fun setTextState(itemView: View, isExpand: Boolean) {
        if (isExpand) {
            itemView.txt_content.maxLines = Integer.MAX_VALUE
            itemView.txt_state.text = "收起"
        } else {
            itemView.txt_content.maxLines = 4
            itemView.txt_state.text = "全文"
        }
    }


    override fun getItemViewType(position: Int): Int {
        return moments[position].viewType
    }

    fun updateNewsList(moments: MutableList<MomentsData>) {
        this.moments = moments
        notifyItemRangeChanged(0, moments.size)
    }
}

class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView)