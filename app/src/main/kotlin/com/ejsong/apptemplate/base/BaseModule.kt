package com.ejsong.apptemplate.base

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseModule {

    companion object {

        // Coroutine providers
        @Provides
        @Singleton
        @com.ejsong.apptemplate.base.inject.Application
        fun appScope(): CoroutineScope = MainScope()

        @Provides
        @Singleton
        @com.ejsong.apptemplate.base.inject.Main
        fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @Singleton
        @com.ejsong.apptemplate.base.inject.Default
        fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @Singleton
        @com.ejsong.apptemplate.base.inject.IO
        fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

        // Firebase providers
        @Provides
        @Singleton
        fun firebaseAnalytics(): FirebaseAnalytics = Firebase.analytics

        @Provides
        @Singleton
        fun firebaseCrashlytics(): FirebaseCrashlytics = FirebaseCrashlytics.getInstance()

        @Provides
        @Singleton
        fun crashlyticsTree(
            firebaseCrashlytics: FirebaseCrashlytics
        ): com.ejsong.apptemplate.base.log.CrashlyticsTree =
            com.ejsong.apptemplate.base.log.CrashlyticsTree(firebaseCrashlytics)

        @Provides
        @Singleton
        fun firebaseRemoteConfig(): FirebaseRemoteConfig = Firebase.remoteConfig

        // Misc providers
        @Provides
        @Singleton
        fun flags(remoteConfig: FirebaseRemoteConfig): com.ejsong.apptemplate.base.config.Flags =
            com.ejsong.apptemplate.base.config.Flags(remoteConfig)
    }
}