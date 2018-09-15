package com.raju.socialplatform.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.data.source.local.dao.PostDao
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewPostViewModel @Inject constructor(private val dao: PostDao): ViewModel() {

    private val postResult: MutableLiveData<Boolean> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun postResult(): LiveData<Boolean> {
        return postResult
    }

    fun addPost(post: Post) {
        try {
            dao.add(post)!!
            postResult.postValue(true)
        } catch (ex: Exception) {
            postResult.postValue(false)
        }
    }

    fun disposeElements() {
        compositeDisposable.clear()
    }
}