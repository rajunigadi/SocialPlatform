package com.raju.socialplatform.android.adapters

import com.raju.socialplatform.android.adapters.delegate.base.AdapterDelegate
import com.raju.socialplatform.android.adapters.delegate.base.DelegatingListAdapter
import com.raju.socialplatform.android.adapters.delegate.base.ListAdapterDelegate
import com.raju.socialplatform.data.model.base.ListItem
import com.raju.socialplatform.ui.adapters.delegate.match.PostDelegate
import javax.inject.Inject

class PostAdapter @Inject internal constructor() : DelegatingListAdapter<ListItem>() {

    override fun createDelegates(): Array<AdapterDelegate<MutableList<ListItem>>> {
        val delegate: ListAdapterDelegate<ListItem> = clickable(PostDelegate() as ListAdapterDelegate<ListItem>)
        return arrayOf(delegate)
    }
}