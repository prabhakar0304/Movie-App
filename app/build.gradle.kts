plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}


android {
    namespace = "com.example.moviesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moviesapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}


dependencies {

    // Core AndroidX libraries
    implementation(libs.androidx.core.ktx) // Core Kotlin extensions for AndroidX
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle runtime Kotlin extensions
    implementation(libs.androidx.activity.compose) // Jetpack Compose support for activities

    // Jetpack Compose libraries
    implementation(platform(libs.androidx.compose.bom)) // BOM (Bill of Materials) for Compose
    implementation(libs.androidx.ui) // Jetpack Compose UI components
    implementation(libs.androidx.ui.graphics) // Jetpack Compose UI graphics
    implementation(libs.androidx.ui.tooling.preview) // Preview tooling for Compose UI
    implementation(libs.androidx.material3) // Material3 UI components

    // Retrofit for API requests
    implementation(libs.retrofit) // Retrofit library for HTTP requests
    implementation(libs.converter.gson) // Gson converter for Retrofit

    // ViewModel and LiveData support
    implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel support for Compose

    // Coroutines for asynchronous operations
    implementation(libs.kotlinx.coroutines.android) // Coroutine support for Android

    // Image loading with Coil
    implementation(libs.coil.compose) // Coil image loading for Compose

    // Jetpack Compose UI and Material3
    implementation(libs.androidx.compose.ui.ui)  // Jetpack Compose UI components
    implementation(libs.androidx.compose.material3.material3) // Material3 UI components for Compose
    implementation(libs.androidx.runtime)  // Compose runtime

    // Dependency Injection with Hilt
    implementation(libs.hilt.android) // Hilt for dependency injection
    implementation(libs.androidx.hilt.navigation.compose) // Hilt navigation support for Compose
    kapt(libs.hilt.android.compiler) // Hilt annotation processor for compile-time DI

    // Accompanist library for advanced UI features
    implementation(libs.accompanist.placeholder.material) // Placeholder UI components from Accompanist

    // AndroidX animation library
    implementation(libs.androidx.animation) // Animation support for Jetpack Compose

    // RxJava for reactive programming
    implementation(libs.rxjava3.rxandroid) // RxAndroid support for Android
    implementation(libs.rxjava3.rxjava) // Core RxJava

    // Testing dependencies
    testImplementation(libs.junit) // JUnit for unit testing
    testImplementation(libs.mockito.inline) // Inline mocking with Mockito
    androidTestImplementation(libs.androidx.junit) // JUnit support for Android tests

    // MockK for mocking in tests
    testImplementation(libs.mockito.core) // Core Mockito library for mocking
    androidTestImplementation(libs.ui.test.junit4) // Compose UI testing with JUnit4
    testImplementation(libs.junit.jupiter.api) // JUnit 5 support for testing

    // Android UI testing
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI testing
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM for UI testing with Compose
    androidTestImplementation(libs.androidx.ui.test.junit4) // JUnit4 test support for UI testing
    debugImplementation(libs.androidx.ui.tooling) // UI tooling support for debugging Compose UI
    debugImplementation(libs.androidx.ui.test.manifest) // Manifest test utilities for Compose

}


kapt {
    correctErrorTypes = true
}