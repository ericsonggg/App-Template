plugins {
    id(Plugins.android_app)
    id(Plugins.firebase_crashlytics)
    id(Plugins.firebase_performance)
    id(Plugins.google_services)
    id(Plugins.hilt)
    id(Plugins.jetbrains)
    kotlin("kapt")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Config.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.namespace
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        @Suppress("UnstableApiUsage")
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
}

dependencies {
    kapt(Kapts.androidx_room_kapt)
    kapt(Kapts.hilt_kapt)

    implementation(platform(Deps.compose_bom))
    implementation(platform(Deps.firebase_bom))

    implementation(Deps.androidx_appcompat)
    implementation(Deps.androidx_core)
    implementation(Deps.androidx_room)
    implementation(Deps.androidx_room_ext)
    implementation(Deps.compose_activity)
    implementation(Deps.compose_livedata)
    implementation(Deps.compose_preview)
    implementation(Deps.compose_viewmodel)
    implementation(Deps.firebase_analytics)
    implementation(Deps.firebase_crashlytics)
    implementation(Deps.firebase_performance)
    implementation(Deps.firebase_remote_config)
    implementation(Deps.google_material)
    implementation(Deps.hilt)
    implementation(Deps.material)
    implementation(Deps.navigation_compose)
    implementation(Deps.navigation_feature_module)
    implementation(Deps.navigation_fragment)
    implementation(Deps.navigation_ui)
    implementation(Deps.splash_screen)
    implementation(Deps.stetho)
    implementation(Deps.timber)

    debugImplementation(DebugDeps.compose_preview)
    debugImplementation(DebugDeps.compose_ui_test)

    testImplementation(TestDeps.androidx_room_test)
    testImplementation(TestDeps.junit)

    androidTestImplementation(platform(Deps.compose_bom))
    androidTestImplementation(AndroidTestDeps.androidx_test_junit)
    androidTestImplementation(AndroidTestDeps.androidx_test_espresso)
    androidTestImplementation(AndroidTestDeps.compose_ui_test)
    androidTestImplementation(AndroidTestDeps.navigation)
}

kapt {
    // Allow references to generated code (for hilt)
    correctErrorTypes = true
}