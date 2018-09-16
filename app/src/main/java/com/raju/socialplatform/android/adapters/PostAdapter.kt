package com.raju.socialplatform.android.adapters

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.raju.socialplatform.R
import com.raju.socialplatform.android.fragments.PostDetailFragment
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.utilities.Constants
import com.raju.socialplatform.utilities.ImageUtil

class PostAdapter(private val activity: AppCompatActivity, private val posts: MutableList<Post>):RecyclerView.Adapter<PostAdapter.MyViewHolder>(),
        Filterable {

    var filteredData = mutableListOf<Post>()
    init {
        filteredData = posts;
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.iv_avatar)
        lateinit var ivAvatar: ImageView

        @BindView(R.id.iv_image)
        lateinit var ivImage: ImageView

        @BindView(R.id.tv_name)
        lateinit var tvName: TextView

        @BindView(R.id.tv_message)
        lateinit var tvMessage: TextView

        @BindView(R.id.tv_description)
        lateinit var tvDescription: TextView

        @BindView(R.id.tv_comments)
        lateinit var tvComments: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_post_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = filteredData[position]
        holder.tvName.text = post.name
        holder.tvMessage.text = post.message
        holder.tvDescription.text = post.description
        ImageUtil.loadProfileImage(Constants.PROFILE, holder.ivAvatar)
        ImageUtil.loadImage(Constants.IMAGE, holder.ivImage)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val ft = activity!!.supportFragmentManager.beginTransaction()
            var fragment = PostDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(Constants.KEY_POST, post)
            fragment.arguments = bundle
            ft.replace(R.id.fragment_container, fragment)
            ft.addToBackStack(null)
            ft.commit()
        })
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            protected override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString().toLowerCase()
                if (charString.isEmpty()) {
                    filteredData = posts
                } else {
                    val filteredList = mutableListOf<Post>()
                    for (row in posts) {
                        if (row is Post) {
                            val name = row.name
                            val message = row.message
                            val description = row.description
                            if (name!!.contains(charString) || message!!.contains(charString) || description!!.contains(charString)) {
                                filteredList.add(row)
                            }
                        }
                    }

                    filteredData = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredData
                return filterResults
            }

            protected override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredData = filterResults.values as MutableList<Post>
                notifyDataSetChanged()
            }
        }
    }
}