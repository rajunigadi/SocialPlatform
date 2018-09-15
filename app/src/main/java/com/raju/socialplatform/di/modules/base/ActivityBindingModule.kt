package com.raju.socialplatform.di.modules.base

import com.raju.socialplatform.android.activities.MainActivity
import com.raju.socialplatform.di.PerActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}