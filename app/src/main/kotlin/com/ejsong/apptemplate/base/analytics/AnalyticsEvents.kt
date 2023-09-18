package com.ejsong.apptemplate.base.analytics

/**
 * The abstract representation of an analytics event.
 * Each event must have a [type].
 *
 * If any are added or changed, you must add it to subclasses of [AnalyticsBroker]
 */
sealed class AnalyticsEvent(val type: String) {
    override fun toString(): String = type
}

/* Add all event types here */
class AppStartEvent : AnalyticsEvent("app_start")
class LoginEvent : AnalyticsEvent("login")
class LogoutEvent : AnalyticsEvent("logout")
class SignUpEvent : AnalyticsEvent("sign_up")
class TutorialStartEvent : AnalyticsEvent("tutorial_start")
class TutorialEndEvent : AnalyticsEvent("tutorial_end")
class ViewBannerEvent(val message: String) : AnalyticsEvent("view_banner") {
    override fun toString(): String = "($type, $message)"
}

class ViewScreenEvent(val screen: String) : AnalyticsEvent("screen_view") {
    override fun toString(): String = "($type, $screen)"
}