package com.ejsong.apptemplate.base.analytics

/**
 * Easy to access object to send analytics events.
 * Call [setProvider] in the application to get real data
 */
object Data {
    private var provider: AnalyticsBroker = DumbAnalyticsBroker()

    fun setProvider(provider: AnalyticsBroker) {
        Data.provider = provider
    }

    fun log(event: AnalyticsEvent) {
        provider.log(event)
    }
}