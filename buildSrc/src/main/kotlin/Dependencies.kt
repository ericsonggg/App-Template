@file:Suppress("SpellCheckingInspection")

object Plugins {
    // App module only plugins
    const val android_app = "com.android.application"
    const val android_library = "com.android.library"
    const val firebase_crashlytics = "com.google.firebase.crashlytics"
    const val firebase_performance = "com.google.firebase.firebase-perf"
    const val google_services = "com.google.gms.google-services"

    // App and submodule plugins
    const val hilt = "com.google.dagger.hilt.android"
    const val jetbrains = "org.jetbrains.kotlin.android"
}

/**
 * Classpath dependencies that should be used only in the Project gradle
 */
object Classpaths {
    const val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebase_crashlytics_gradle}"
    const val firebase_performance = "com.google.firebase:perf-plugin:${Versions.firebase_performance}"
    const val google_services = "com.google.gms:google-services:${Versions.google_services}"
}

object Kapts {
    const val androidx_room_kapt = "androidx.room:room-compiler:${Versions.androidx_room}"
    const val hilt_kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}

/**
 * Dependencies that should go in the "dependencies" block
 */
object Deps {
    // Platform deps (use with `implementation platform`)
    const val compose_bom = "androidx.compose:compose-bom:${Versions.compose_bom}"
    const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom}"

    // App/module level deps (use with `implementation`)
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidx_room = "androidx.room:room-runtime:${Versions.androidx_room}"
    const val androidx_room_ext = "androidx.room:room-ktx:${Versions.androidx_room}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val compose_livedata = "androidx.compose.runtime:runtime-livedata"
    const val compose_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose_viewmodel}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebase_performance = "com.google.firebase:firebase-perf-ktx"
    const val firebase_remote_config = "com.google.firebase:firebase-config-ktx"
    const val google_material = "com.google.android.material:material:${Versions.google_material}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val material = "androidx.compose.material3:material3"
    const val navigation_compose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val navigation_feature_module = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val splash_screen = "androidx.core:core-splashscreen:${Versions.splash_screen}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

/**
 * Debug-only dependencies that should be used with "debugImplementation"
 */
object DebugDeps {
    const val compose_preview = "androidx.compose.ui:ui-tooling"
    const val compose_ui_test = "androidx.compose.ui:ui-test-manifest"
}

/**
 * Test-only dependencies that should be used with "testImplementation"
 */
object TestDeps {
    const val androidx_room_test = "androidx.room:room-testing:${Versions.androidx_room}"
    const val junit = "junit:junit:${Versions.junit}"
}

/**
 * Android Test-only dependencies that should be used with "androidTestImplementation"
 */
object AndroidTestDeps {
    const val androidx_test_junit = "androidx.test.ext:junit:${Versions.androidx_test_junit}"
    const val androidx_test_espresso = "androidx.test.espresso:espresso-core:${Versions.androidx_test_espresso}"
    const val compose_ui_test = "androidx.compose.ui:ui-test-junit4"
    const val navigation = "androidx.navigation:navigation-testing:${Versions.navigation}"
}