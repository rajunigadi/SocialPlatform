package com.raju.socialplatform

import android.app.Activity
import android.app.Application
import com.raju.socialplatform.di.components.DaggerAppComponent
import com.raju.socialplatform.utilities.logger.DebugLogTree
import com.raju.socialplatform.utilities.logger.ReleaseLogTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class SocialApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        } else {
            Timber.plant(ReleaseLogTree())
        }

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}