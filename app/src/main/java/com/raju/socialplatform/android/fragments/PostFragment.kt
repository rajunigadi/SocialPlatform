package com.raju.socialplatform.android.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.raju.socialplatform.R
import com.raju.socialplatform.android.adapters.PostAdapter
import com.raju.socialplatform.android.fragments.base.BaseFragment
import com.raju.socialplatform.android.viewmodel.PostViewModel
import com.raju.socialplatform.data.model.Post
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class PostFragment: BaseFragment(R.layout.fragment_post, "Post") {

    @BindView(R.id.recycler_view)
    lateinit protected var recyclerView: RecyclerView

    @Inject
    lateinit var viewModel: PostViewModel

    var adapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()

        showLoading()
        viewModel.loadPosts()

        viewModel.postResult().observe(this, Observer<MutableList<Post>> {
            Timber.d("Post onChange() ${it?.size}")
            adapter = PostAdapter(activity!! as AppCompatActivity, it!!)
            recyclerView.adapter = adapter
            hideLoading()
        })

        viewModel.postError().observe(this, Observer<String> {
            showError(it!!)
            hideLoading()
        })
    }

    override fun onStop() {
        viewModel.disposeElements()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                // lets search
                return false
            }
            else -> {
            }
        }

        return false
    }

    @OnClick(R.id.fab_add)
    fun onNewClick(view: View) {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, NewPostFragment())
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
    }
}