package com.raju.socialplatform.data.source.local.dao

import android.arch.persistence.room.*
import com.raju.socialplatform.data.model.Post
import io.reactivex.Flowable

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(post: Post)

    @Query("SELECT * FROM post ORDER BY id")
    fun getPosts(): Flowable<MutableList<Post>>
}