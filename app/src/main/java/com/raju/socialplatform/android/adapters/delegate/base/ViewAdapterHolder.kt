package com.raju.socialplatform.android.adapters.delegate.base

interface ViewAdapterHolder<T> {
    fun setData(position: Int, data: T)
}