package com.raju.socialplatform.android.adapters.delegate.base

import android.view.View

interface ItemClickListener<T> {
    fun onItemClick(view: View, position: Int, item: T)
}