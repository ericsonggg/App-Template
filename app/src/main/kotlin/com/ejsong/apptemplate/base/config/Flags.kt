package com.ejsong.apptemplate.base.config

import com.ejsong.apptemplate.R
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import timber.log.Timber
import javax.inject.Inject

/**
 * Firebase remote config backed flags.
 * Used to gate specific behaviors
 */
class Flags @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
) {

    private val listeners: MutableMap<String, () -> Unit> = mutableMapOf()

    /**
     * Initialize Firebase Remote Config.
     * [isDebug] determines how fast the refreshes are
     */
    fun init(isDebug: Boolean = false) {
        with(remoteConfig) {
            // Set timeout
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = if (isDebug) MIN_INTERVAL_DEBUG else MIN_INTERVAL_PROD
                }
            )

            // Set in-app defaults
            setDefaultsAsync(R.xml.remote_config_defaults)

            // Fetch the latest
            refresh()

            // Setup real time updates
            addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    Timber.i("Updated keys: ${configUpdate.updatedKeys}")
                    // Run the listeners for the keys that were updated
                    remoteConfig.activate().addOnCompleteListener {
                        listeners.filter { (key, _) -> configUpdate.updatedKeys.contains(key) }
                            .forEach { (key, runner) ->
                                Timber.d("Running update for $key")
                                runner()
                            }
                    }
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    Timber.e(error, "Failed to get real time remote config, error code: ${error.code}")
                }
            })
        }
    }

    /**
     * Get the latest flags from Firebase and apply them
     */
    fun refresh() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.i("Successfully fetched remote config")
                } else {
                    Timber.w("Failed to fetch remote config", task.exception)
                }
            }
    }

    /**
     * Add a real time config listener that activates [listener] when [key] is updated.
     * The [listener] should manually invoke the relevant getter to get the new value.
     *
     * If the [key] already exists, this function replaces the old listener
     *
     * @return true if the key was not replaced. False if an old key was replaced.
     */
    fun addRealTimeListener(key: String, listener: () -> Unit): Boolean {
        return if (listeners.contains(key)) {
            listeners.replace(key, listener)
            true
        } else {
            listeners[key] = listener
            false
        }
    }

    /**
     * Removes the entry for [key] if it exists.
     */
    fun removeRealTimeListener(key: String) {
        listeners.remove(key)
    }

    // Getters
    fun getBanner(): String = remoteConfig.getString(FlagKeys.BANNER)

    companion object {
        const val MIN_INTERVAL_DEBUG = 60L
        const val MIN_INTERVAL_PROD = 3600L
    }
}