package com.raju.socialplatform.android.adapters.delegate.base

interface ClickableItemTarget<T> {
    fun setOnItemClickListener(listener: ItemClickListener<T>)
}