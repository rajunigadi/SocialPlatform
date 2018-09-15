package com.raju.socialplatform.di.modules.base

import android.app.Application
import android.content.Context
import com.raju.socialplatform.SocialApp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    internal fun provideContext(application: SocialApp): Context {
        return application.getApplicationContext()
    }

    @Provides
    internal fun provideApplication(application: SocialApp): Application {
        return application
    }
}