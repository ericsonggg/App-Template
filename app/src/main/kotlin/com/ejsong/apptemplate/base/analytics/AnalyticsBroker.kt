package com.ejsong.apptemplate.base.analytics

/**
 * A broker for analytics info
 */
interface AnalyticsBroker {

    /**
     * Log an [event], asynchronously
     */
    fun log(event: AnalyticsEvent)
}