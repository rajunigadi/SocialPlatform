package com.raju.socialplatform.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.data.model.base.ListItem
import com.raju.socialplatform.data.source.repositories.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: PostRepository): ViewModel() {

    private val postResult: MutableLiveData<MutableList<ListItem>> = MutableLiveData()
    private val postError: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun postResult(): LiveData<MutableList<ListItem>> {
        return postResult
    }

    fun postError(): LiveData<String> {
        return postError
    }

    fun loadMatches() {
        compositeDisposable.add(repository.getPosts()!!
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        Consumer <MutableList<ListItem>> {
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