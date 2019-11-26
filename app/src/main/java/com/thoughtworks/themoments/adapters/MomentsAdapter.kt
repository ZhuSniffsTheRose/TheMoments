package com.thoughtworks.themoments.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thoughtworks.themoments.R
import com.thoughtworks.themoments.bean.*
import com.thoughtworks.themoments.loadOnlineImg
import com.thoughtworks.themoments.widget.NineImageAdapter
import kotlinx.android.synthetic.main.include_item_recycler_content.view.*
import kotlinx.android.synthetic.main.item_recycler_header.view.*
import kotlinx.android.synthetic.main.item_recycler_moments_word.view.comments_view
import kotlinx.android.synthetic.main.item_recycler_moments_word.view.img_avatar
import kotlinx.android.synthetic.main.item_recycler_moments_word.view.txt_user_name
import kotlinx.android.synthetic.main.item_recycler_moments_word_pic.view.*

/**
 * Created by Zhu on 2019-11-22
 */
class MomentsAdapter(var moments: MutableList<MomentsData> = arrayListOf()) :
    RecyclerView.Adapter<BaseHolder>() {

    private val TAG = MomentsAdapter::class.java.simpleName

    private val HEADER_SIZE = 1

    private var mHeaderUserInfo: UserInfoBean? = null

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) =
        BaseHolder(
            LayoutInflater.from(container.context).inflate(
                when (viewType) {
                    TYPE_MOMENTS_CONTENT_PIC -> R.layout.item_recycler_moments_word_pic
                    TYPE_MOMENTS_CONTENT -> R.layout.item_recycler_moments_word
                    TYPE_MOMENTS_PIC -> R.layout.item_recycler_moments_pic
                    else -> R.layout.item_recycler_header
                }, container, false
            )
        )

    override fun getItemCount() = moments.size + HEADER_SIZE

    fun setHeaderUserInfo(info: UserInfoBean) {
        mHeaderUserInfo = info
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (mHeaderUserInfo == null) {
            return
        }

        val itemType = getItemViewType(position)
        if (itemType == TYPE_MOMENTS_HEADER) {
            holder.itemView.profile_user_name.text = mHeaderUserInfo!!.nick
            loadOnlineImg(
                holder.itemView.context,
                mHeaderUserInfo!!.profileImage,
                holder.itemView.profile_bg_img
            )
            loadOnlineImg(
                holder.itemView.context,
                mHeaderUserInfo!!.avatar,
                holder.itemView.profile_avatar_img
            )
            return
        }

        val momentsData = moments[position - HEADER_SIZE]
        if (momentsData.sender == null) {
            Log.e(TAG, "sender in MomentsAdapter is null")
            return
        }

        setCommonInfo(holder.itemView, momentsData)

        when (itemType) {
            TYPE_MOMENTS_CONTENT_PIC -> holder.itemView.let {
                it.nine_grid_view.setAdapter(
                    NineImageAdapter(
                        it.context,
                        momentsData.images!!
                    )
                )

                setContentInfo(
                    it,
                    momentsData.content,
                    momentsData.needShowAllSign,
                    momentsData.isExpand
                )
                holder.itemView.txt_state.setOnClickListener {
                    momentsData.isExpand = !momentsData.isExpand
                    setTextState(it, momentsData.isExpand)
                }
            }

            TYPE_MOMENTS_CONTENT -> holder.itemView.let {
                it.txt_content.text = momentsData.content

                setContentInfo(
                    it,
                    momentsData.content,
                    momentsData.needShowAllSign,
                    momentsData.isExpand
                )
                it.txt_state.setOnClickListener {
                    momentsData.isExpand = !momentsData.isExpand
                    setTextState(it, momentsData.isExpand)
                }
            }


            TYPE_MOMENTS_PIC -> holder.itemView.let {
                it.nine_grid_view.setAdapter(
                    NineImageAdapter(
                        it.context,
                        momentsData.images!!
                    )
                )
            }
        }
    }

    private fun setCommonInfo(itemView: View, momentsData: MomentsData) {
        setSenderInfo(itemView, momentsData.sender!!)
        itemView.comments_view.setComments(momentsData.comments)
    }

    private fun setSenderInfo(itemView: View, sender: SenderBean) {
        itemView.txt_user_name.text = sender.nick

        Glide.with(itemView.context)
            .asDrawable().load(sender.avatar)
            .apply(
                RequestOptions.centerCropTransform()
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.ic_launcher_foreground)
            )
            .into(itemView.img_avatar)
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
        if (position == 0) {
            return TYPE_MOMENTS_HEADER
        }

        return moments[position - HEADER_SIZE].viewType
    }

    fun updateNewsList(moments: MutableList<MomentsData>) {
        this.moments = moments
        notifyItemRangeChanged(0, moments.size + HEADER_SIZE)
    }
}

class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView)