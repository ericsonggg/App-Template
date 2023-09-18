package com.ejsong.apptemplate

import com.ejsong.apptemplate.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Hilt supported Android Application, for PROD
 */
@HiltAndroidApp
class MainApplication : BaseApplication() {

    @Inject
    lateinit var analyticsBroker: FirebaseAnalyticsBroker

    override fun onCreate() {
        super.onCreate()

        // Firebase Remote Config
        flags.init(isDebug = true)

        // Analytics
        Data.setProvider(analyticsBroker)
    }
}