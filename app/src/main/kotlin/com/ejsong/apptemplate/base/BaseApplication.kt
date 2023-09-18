package com.ejsong.apptemplate.base

import android.app.Application
import com.ejsong.apptemplate.BuildConfig
import timber.log.Timber
import javax.inject.Inject

open class BaseApplication : Application() {

    @Inject
    lateinit var crashlyticsTree: com.ejsong.apptemplate.base.log.CrashlyticsTree

    @Inject
    lateinit var flags: com.ejsong.apptemplate.base.config.Flags

    override fun onCreate() {
        super.onCreate()

        // Setup Timber
        Timber.plant(crashlyticsTree)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}