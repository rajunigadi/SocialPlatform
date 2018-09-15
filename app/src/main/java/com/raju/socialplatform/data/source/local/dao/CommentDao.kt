package com.raju.socialplatform.data.source.local.dao

import android.arch.persistence.room.*
import com.raju.socialplatform.data.model.Comment
import io.reactivex.Flowable

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(comment: Comment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(comments: List<Comment>)

    @Update
    fun update(comment: Comment)

    @Query("SELECT * FROM comment WHERE postId =:postId ORDER BY id")
    fun getComments(postId: Int): Flowable<MutableList<Comment>>
}