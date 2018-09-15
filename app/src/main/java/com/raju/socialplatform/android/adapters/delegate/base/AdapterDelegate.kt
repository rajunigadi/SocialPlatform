package com.raju.socialplatform.android.adapters.delegate.base

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface AdapterDelegate<T> {
    fun isForViewType(@NonNull item: T, position: Int): Boolean
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(@NonNull item: T, position: Int, holder: RecyclerView.ViewHolder)
    fun setDelegateClickListener(delegateClickListener: DelegateClickListener)
}