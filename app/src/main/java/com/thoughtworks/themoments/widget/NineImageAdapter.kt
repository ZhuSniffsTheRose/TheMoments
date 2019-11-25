package com.thoughtworks.themoments.widget

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.thoughtworks.themoments.R
import com.thoughtworks.themoments.bean.ImageBean
import com.thoughtworks.themoments.dp2px
import com.thoughtworks.themoments.getScreenWidth

/**
 * Created by Zhu on 2019-11-25
 */
class NineImageAdapter(
    val mContext: Context,
    val mImageBeans: MutableList<ImageBean>
) : NineGridView.NineGridAdapter {


    private var mRequestOptions: RequestOptions

    private var mDrawableTransitionOptions: DrawableTransitionOptions

    override fun getCount() = mImageBeans.size

    init {
        val itemSize = (getScreenWidth() - 2 * dp2px(4f) - dp2px(54f)) / 3
        mDrawableTransitionOptions = DrawableTransitionOptions.withCrossFade()
        mRequestOptions = RequestOptions().centerCrop().error(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_foreground).override(itemSize, itemSize)

    }

    override fun getItem(position: Int) = mImageBeans[position].url

    override fun getView(position: Int, itemView: View?): View {
        val imageView: ImageView
        if (itemView == null) {
            imageView = ImageView(mContext)
            imageView.setBackgroundColor(Color.parseColor("#F2F2F2"))
            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        } else {
            imageView = itemView as ImageView
        }
        val url = mImageBeans[position].url

        Glide.with(mContext).load(url)
            .apply(mRequestOptions).transition(mDrawableTransitionOptions)
            .into(imageView)
        return imageView
    }


}
