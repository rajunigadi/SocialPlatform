package com.raju.socialplatform.di.modules.base

import com.raju.socialplatform.android.fragments.NewPostFragment
import com.raju.socialplatform.android.fragments.PostDetailFragment
import com.raju.socialplatform.android.fragments.PostFragment
import com.raju.socialplatform.di.PerFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindPostFragment(): PostFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindNewPostFragment(): NewPostFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindPostDetailFragment(): PostDetailFragment
}