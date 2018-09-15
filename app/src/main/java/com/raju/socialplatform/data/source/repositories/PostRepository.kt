package com.raju.socialplatform.data.source.repositories

import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.data.model.base.ListItem
import com.raju.socialplatform.data.source.local.dao.PostDao
import io.reactivex.Observable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class PostRepository @Inject constructor(private val dao: PostDao) {

    fun getPosts(): Observable<MutableList<ListItem>>? {
        var listItems = mutableListOf<ListItem>()
        listItems.addAll(dao.getPosts())
        return Observable.just(listItems)
    }
}