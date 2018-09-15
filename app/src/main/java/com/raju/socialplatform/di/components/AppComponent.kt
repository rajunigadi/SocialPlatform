package com.raju.socialplatform.di.components

import com.raju.socialplatform.SocialApp
import com.raju.socialplatform.di.modules.base.ActivityBindingModule
import com.raju.socialplatform.di.modules.base.ApplicationModule
import com.raju.socialplatform.di.modules.base.DatabaseModule
import com.raju.socialplatform.di.modules.base.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        DatabaseModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: SocialApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: SocialApp)
}