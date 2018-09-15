package com.raju.socialplatform.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raju.socialplatform.data.model.Comment
import com.raju.socialplatform.data.source.local.dao.CommentDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentViewModel @Inject constructor(private val dao: CommentDao): ViewModel() {

    private val commentsResult: MutableLiveData<MutableList<Comment>> = MutableLiveData()
    private val commentsError: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun commentsResult(): LiveData<MutableList<Comment>> {
        return commentsResult
    }

    fun commentsError(): LiveData<String> {
        return commentsError
    }

    fun loadComments(postId: Int) {
        compositeDisposable.add(dao.getComments(postId)!!.toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        Consumer <MutableList<Comment>> {
                            it -> commentsResult.postValue(it)
                        },
                        Consumer {
                            t -> commentsError.postValue(t.message)
                        }
                ))
    }







    private val addCommentResult: MutableLiveData<Boolean> = MutableLiveData()

    fun addCommentResult(): LiveData<Boolean> {
        return addCommentResult
    }

    fun addComment(comment: Comment) {
        try {
            dao.add(comment)!!
            addCommentResult.postValue(true)
        } catch (ex: Exception) {
            addCommentResult.postValue(false)
        }
    }

    fun disposeElements() {
        compositeDisposable.clear()
    }
}