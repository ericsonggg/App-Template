package com.ejsong.apptemplate.base.analytics

import com.ejsong.apptemplate.base.inject.Application
import com.ejsong.apptemplate.base.inject.Default
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Event
import com.google.firebase.analytics.FirebaseAnalytics.Param
import com.google.firebase.analytics.ktx.logEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * A broker that sends data to Firebase Analytics
 */
class FirebaseAnalyticsBroker @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    @Application private val appScope: CoroutineScope,
    @Default private val dispatcher: CoroutineDispatcher,
) : AnalyticsBroker {

    override fun log(event: AnalyticsEvent) {
        appScope.launch(dispatcher) {
            logToFirebase(event)
            Timber.d("Logged event: $event")
        }
    }

    /**
     * Logs events to Firebase.
     * If it is a custom event, defers it to [logCustomEventToFirebase]
     */
    private fun logToFirebase(event: AnalyticsEvent) {
        with(firebaseAnalytics) {
            // Verify against Firebase constants
            when (event) {
                is AppStartEvent -> logEvent(Event.APP_OPEN)
                is LoginEvent -> logEvent(Event.LOGIN)
                is SignUpEvent -> logEvent(Event.SIGN_UP)
                is TutorialStartEvent -> logEvent(Event.TUTORIAL_BEGIN)
                is TutorialEndEvent -> logEvent(Event.TUTORIAL_COMPLETE)
                else -> logCustomEventToFirebase(event)
            }
        }
    }

    /**
     * Logs only custom events to Firebase
     */
    private fun logCustomEventToFirebase(event: AnalyticsEvent) {
        firebaseAnalytics.logEvent(event.type) {
            when (event) {
                is LogoutEvent -> {}
                is ViewScreenEvent -> param(Param.SCREEN_NAME, event.screen)
                is ViewBannerEvent -> param(MessageParam.type, event.message)
                else -> {
                    Timber.e("Tried to log unknown custom event with ${event::class.simpleName}")
                }
            }
        }
    }
}

/**
 * Log a [name] event with Firebase without any params
 */
fun FirebaseAnalytics.logEvent(name: String) {
    logEvent(name, null)
}