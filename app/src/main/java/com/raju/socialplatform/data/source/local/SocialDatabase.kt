package com.raju.socialplatform.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.raju.socialplatform.data.model.Comment
import com.raju.socialplatform.data.model.Post
import com.raju.socialplatform.data.source.local.dao.CommentDao
import com.raju.socialplatform.data.source.local.dao.PostDao

@Database(entities = [Post::class, Comment::class], version = 1)
abstract class SocialDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}