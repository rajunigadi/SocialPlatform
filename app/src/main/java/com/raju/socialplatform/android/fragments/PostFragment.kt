package com.raju.socialplatform.android.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import com.raju.socialplatform.R
import com.raju.socialplatform.android.adapters.PostAdapter
import com.raju.socialplatform.android.adapters.delegate.base.ClickableItemTarget
import com.raju.socialplatform.android.adapters.delegate.base.ItemClickListener
import com.raju.socialplatform.android.fragments.base.BaseFragment
import com.raju.socialplatform.android.viewmodel.PostViewModel
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.data.model.base.ListItem
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class PostFragment: BaseFragment(R.layout.fragment_post, "Post"), ItemClickListener<Post> {

    @BindView(R.id.recycler_view)
    lateinit protected var recyclerView: RecyclerView

    @Inject
    lateinit var viewModel: PostViewModel

    @Inject
    lateinit var adapter: PostAdapter

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
        setupAdapter(recyclerView)
    }

    override fun onStart() {
        super.onStart()

        showLoading()
        viewModel.loadMatches()

        viewModel.postResult().observe(this, Observer<MutableList<ListItem>> {
            Timber.d("Post onChange() ${it?.size}")
            adapter.refactorItems(it!!)
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
            R.id.action_add -> {
                val ft = activity!!.getSupportFragmentManager().beginTransaction()
                ft.replace(R.id.fragment_container, NewPostFragment())
                ft.addToBackStack(null)
                ft.commit()
                return false
            }
            else -> {
            }
        }

        return false
    }

    override fun onItemClick(view: View, position: Int, item: Post) {
        Timber.d("Item clicked")
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        if (recyclerView.adapter !is PostAdapter) {
            recyclerView.adapter = adapter

            val target = adapter as ClickableItemTarget<Post>
            target.setOnItemClickListener(this)

            adapter.setup()
        }
    }
}