package com.raju.socialplatform.android.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.raju.socialplatform.R
import com.raju.socialplatform.android.fragments.base.BaseFragment
import com.raju.socialplatform.android.viewmodel.NewPostViewModel
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.utilities.ValidationUtil
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewPostFragment: BaseFragment(R.layout.fragment_new_post, "New Post") {

    @BindView(R.id.et_message)
    lateinit var etMessage: AppCompatEditText

    @BindView(R.id.et_description)
    lateinit var etDescription: AppCompatEditText

    @Inject
    lateinit var viewModel: NewPostViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set up UI
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        viewModel.disposeElements()
        super.onStop()
    }

    @OnClick(R.id.btn_post)
    fun onPostClick(view: View) {
        if(checkValidation()) {
            hideKeyboard();
            addNewPost()
        }
    }

    private fun checkValidation(): Boolean {
        var ret = true
        if (!ValidationUtil.hasText(etMessage))
            ret = false
        if (!ValidationUtil.hasText(etDescription))
            ret = false
        return ret
    }

    private fun addNewPost() {
        showLoading()
        var post = Post()
        post.name = "New User"
        post.message = etMessage.text.toString()
        post.description = etDescription.text.toString()

        viewModel.addPost(post)

        viewModel.postResult().observe(this, Observer<Boolean> {
            hideLoading()
            if(it!!) {
                showSnackBar("Post added")
                activity!!.supportFragmentManager.popBackStack()
            } else {
                showSnackBar("Unable to add post, try later.")
            }
        })
    }
}