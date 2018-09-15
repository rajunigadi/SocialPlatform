package com.raju.socialplatform.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.raju.socialplatform.data.model.*
import com.raju.socialplatform.data.source.local.dao.CommentDao
import com.raju.socialplatform.data.source.local.dao.PostDao

@Database(entities = arrayOf(Post::class, Comment::class), version = 2)
abstract class SocialDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}