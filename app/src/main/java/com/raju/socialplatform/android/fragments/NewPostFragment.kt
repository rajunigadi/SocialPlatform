package com.raju.socialplatform.android.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import com.raju.socialplatform.R
import com.raju.socialplatform.android.fragments.base.BaseFragment
import com.raju.socialplatform.android.viewmodel.PostViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewPostFragment: BaseFragment(R.layout.fragment_new_post, "New Post") {

    @Inject
    lateinit var viewModel: PostViewModel

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
}