package com.raju.socialplatform.utilities.logger

import android.os.Build
import android.util.Log
import timber.log.Timber

class DebugLogTree: Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String?, thowable: Throwable?) {
        var prior = priority
        if(Build.MANUFACTURER.equals("HUAWEI") || Build.MANUFACTURER.equals("samsung")) {
            if(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                prior = Log.ERROR;
            }
        }
        super.log(prior, tag, message, thowable)
    }

    override fun createStackElementTag(element: StackTraceElement?): String {
        if(element == null) return super.createStackElementTag(element) else return super.createStackElementTag(element) + " - " + element.lineNumber
    }
}