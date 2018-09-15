package com.raju.socialplatform.di.modules.base

import com.raju.socialplatform.android.fragments.PostFragment
import com.raju.socialplatform.dagger.modules.base.ViewModelModule
import com.raju.socialplatform.di.PerFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(AndroidInjectionModule::class, ViewModelModule::class))
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindPostFragment(): PostFragment
}