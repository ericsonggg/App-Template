package com.ejsong.apptemplate.base.analytics

/**
 * An [AnalyticsBroker] that does nothing
 */
class DumbAnalyticsBroker : AnalyticsBroker {

    override fun log(event: AnalyticsEvent) {
        /* Do nothing */
    }
}