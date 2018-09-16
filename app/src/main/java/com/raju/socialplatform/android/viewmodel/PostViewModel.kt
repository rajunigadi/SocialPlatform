package com.raju.socialplatform.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.data.source.local.dao.PostDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(private val dao: PostDao): ViewModel() {

    private val postResult: MutableLiveData<MutableList<Post>> = MutableLiveData()
    private val postError: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun postResult(): LiveData<MutableList<Post>> {
        return postResult
    }

    fun postError(): LiveData<String> {
        return postError
    }

    fun loadPosts() {
        compositeDisposable.add(dao.getPosts()!!.toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        Consumer <MutableList<Post>> {
                            it -> postResult.postValue(it)
                        },
                        Consumer {
                            t -> postError.postValue(t.message)
                        }
                ))
    }

    fun disposeElements() {
        compositeDisposable.clear()
    }
}