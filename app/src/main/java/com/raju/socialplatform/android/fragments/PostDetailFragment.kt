package com.raju.socialplatform.android.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.raju.socialplatform.R
import com.raju.socialplatform.android.adapters.CommentAdapter
import com.raju.socialplatform.android.fragments.base.BaseFragment
import com.raju.socialplatform.android.viewmodel.CommentViewModel
import com.raju.socialplatform.data.model.Comment
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.utilities.Constants
import com.raju.socialplatform.utilities.ValidationUtil
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostDetailFragment: BaseFragment(R.layout.fragment_post_detail, "Post Detail") {

    /*@BindView(R.id.iv_avatar)
    lateinit var ivAvatar: ImageView

    @BindView(R.id.tv_name)
    lateinit var tvName: TextView

    @BindView(R.id.tv_message)
    lateinit var tvMessage: TextView

    @BindView(R.id.tv_description)
    lateinit var tvDescription: TextView

    @BindView(R.id.iv_image)
    lateinit var ivImage: ImageView*/

    @BindView(R.id.et_comment)
    lateinit var etComment: AppCompatEditText

    @BindView(R.id.recycler_view)
    lateinit protected var recyclerView: RecyclerView

    @Inject
    lateinit var viewModel: CommentViewModel

    var adapter: CommentAdapter? = null

    private var post: Post? = null;

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments;
        post = bundle?.getParcelable(Constants.KEY_POST)

        /*tvName.text = post?.name
        tvMessage.text = post?.message
        tvDescription.text = post?.description
        ImageUtil.loadProfileImage(Constants.PROFILE, ivAvatar)
        ImageUtil.loadImage(Constants.IMAGE, ivImage)*/

        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadComments()
    }

    override fun onStop() {
        viewModel.disposeElements()
        super.onStop()
    }

    @OnClick(R.id.btn_comment)
    fun onCommentClick(view: View) {
        if(checkValidation()) {
            hideKeyboard();
            addNewComment()
        }
    }

    private fun loadComments() {
        showLoading()
        viewModel.loadComments(post!!.id)

        viewModel.commentsResult().observe(this, Observer<MutableList<Comment>> {
            adapter = CommentAdapter(it!!)
            recyclerView.adapter = adapter
            hideLoading()
        })

        viewModel.commentsError().observe(this, Observer<String> {
            showError(it!!)
            hideLoading()
        })
    }

    private fun checkValidation(): Boolean {
        var ret = true
        if (!ValidationUtil.hasText(etComment))
            ret = false
        return ret
    }

    private fun addNewComment() {
        showLoading()
        var comment = Comment()
        comment.postId = post!!.id
        comment.comment = etComment.text.toString()
        comment.name = "New User"

        viewModel.addComment(comment)

        viewModel.addCommentResult().observe(this, Observer<Boolean> {
            hideLoading()
            if(it!!) {
                //showSnackBar("Comment added")
                etComment.setText("")
                loadComments()
            } else {
                showSnackBar("Unable to add comment, try later.")
            }
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
    }
}