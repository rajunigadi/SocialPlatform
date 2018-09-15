package com.raju.socialplatform.android.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.raju.socialplatform.R
import com.raju.socialplatform.data.model.Comment
import com.raju.socialplatform.utilities.Constants
import com.raju.socialplatform.utilities.ImageUtil

class CommentAdapter(private val comments: MutableList<Comment>):RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.iv_avatar)
        lateinit var ivAvatar: ImageView

        @BindView(R.id.tv_name)
        lateinit var tvName: TextView

        @BindView(R.id.tv_comment)
        lateinit var tvComment: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_comment_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = comments[position]
        holder.tvName.text = comment.name
        holder.tvComment.text = comment.comment
        ImageUtil.loadProfileImage(Constants.PROFILE, holder.ivAvatar)
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}