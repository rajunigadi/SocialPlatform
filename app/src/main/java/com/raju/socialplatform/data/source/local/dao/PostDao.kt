package com.raju.socialplatform.data.source.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.raju.socialplatform.data.model.Post
import io.reactivex.Flowable

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(posts: List<Post>)

    @Update
    fun update(post: Post)

    @Query("SELECT * FROM post ORDER BY id")
    fun getPosts(): MutableList<Post>
}