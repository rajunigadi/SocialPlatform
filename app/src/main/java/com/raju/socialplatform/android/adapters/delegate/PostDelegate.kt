package com.raju.socialplatform.ui.adapters.delegate.match

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.raju.socialplatform.R
import com.raju.socialplatform.android.adapters.delegate.base.ListAdapterDelegate
import com.raju.socialplatform.android.adapters.delegate.base.ViewAdapterHolder
import com.raju.socialplatform.data.model.Post

class PostDelegate constructor(): ListAdapterDelegate<Post>(R.layout.layout_post_item, Post::class.java) {

    override fun formViewHolder(view: View): RecyclerView.ViewHolder {
        return MatchViewHolder(view)
    }

    protected inner class MatchViewHolder constructor(private var view: View): AdapterViewHolder(view), ViewAdapterHolder<Post> {

        override fun setData(position: Int, data: Post) {
            tvName.text = data.message
            tvDescription.text = data.description
        }

        @BindView(R.id.iv_icon)
        lateinit var ivIcon: ImageView

        @BindView(R.id.tv_name)
        lateinit var tvName: TextView

        @BindView(R.id.tv_description)
        lateinit var tvDescription: TextView


    }
}