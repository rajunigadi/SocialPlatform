package com.raju.socialplatform.di.modules.base

import android.app.Application
import android.arch.persistence.room.Room
import com.raju.socialplatform.data.source.local.SocialDatabase
import com.raju.socialplatform.data.source.local.dao.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    internal fun providesDatabase(application: Application): SocialDatabase {
        return Room
                .databaseBuilder(application, SocialDatabase::class.java, "social_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    internal fun providesPostDao(database: SocialDatabase): PostDao {
        return database.postDao()
    }
}