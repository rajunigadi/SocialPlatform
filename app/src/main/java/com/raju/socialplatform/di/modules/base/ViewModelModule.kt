package com.raju.socialplatform.dagger.modules.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.raju.socialplatform.android.viewmodel.PostViewModel
import com.raju.socialplatform.android.viewmodel.base.SocialViewModelFactory
import com.raju.socialplatform.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: SocialViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}