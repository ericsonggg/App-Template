// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.android_app) version Versions.android_gradle apply false
    id(Plugins.android_library) version Versions.android_gradle apply false
    id(Plugins.jetbrains) version Versions.kotlin apply false
    id(Plugins.hilt) version Versions.hilt apply false
    id(Plugins.firebase_performance) version Versions.firebase_performance apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Classpaths.firebase_crashlytics)
        classpath(Classpaths.firebase_performance)
        classpath(Classpaths.google_services)
    }
}