package com.ejsong.apptemplate.base.analytics

/**
 * Abstract representation of an analytics event.
 * Each param must have a [type].
 *
 * If any are added or changed, you must add it to subclasses of [AnalyticsBroker]
 */
sealed class AnalyticsParam(val type: String) {
    override fun toString() = type
}

/* Add all param types here */
object MessageParam : AnalyticsParam("message")