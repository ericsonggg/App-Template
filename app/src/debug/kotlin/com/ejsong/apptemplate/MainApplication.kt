package com.ejsong.apptemplate

import com.ejsong.apptemplate.base.BaseApplication
import com.ejsong.apptemplate.base.analytics.Data
import com.ejsong.apptemplate.base.analytics.FirebaseAnalyticsBroker
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Hilt supported Android Application, only for debug
 */
@HiltAndroidApp
class MainApplication : BaseApplication() {

    @Inject
    lateinit var analyticsBroker: FirebaseAnalyticsBroker

    override fun onCreate() {
        super.onCreate()

        // Stetho
        Stetho.initializeWithDefaults(this)

        // Firebase Remote Config
        flags.init(isDebug = false)

        // Analytics
        Data.setProvider(analyticsBroker)
    }
}