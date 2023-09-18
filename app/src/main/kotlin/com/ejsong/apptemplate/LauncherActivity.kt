package com.ejsong.apptemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ejsong.apptemplate.home.HomeActivity

/**
 * Splash screen activity that handles routing and initial setup
 */
class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }

        // Add routing to other activities here
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}