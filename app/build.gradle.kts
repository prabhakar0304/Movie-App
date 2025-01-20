plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    id("com.google.devtools.ksp")
//    alias(libs.plugins.kotlin.kapt)
    id("com.google.dagger.hilt.android")
//    id("kotlin-kapt")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(platform(libs.androidx.compose.bom.v20240301))
    implementation(libs.material3)
    implementation(libs.ui)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Coroutines for background tasks
    implementation(libs.kotlinx.coroutines.android)

    // Coil for image loading
    implementation(libs.coil.compose)

    implementation (libs.androidx.ui.v1xx)


    implementation(libs.androidx.compose.ui.ui)  // Jetpack Compose UI
    implementation(libs.androidx.compose.material3.material3) // Material3
    implementation(libs.androidx.runtime)  // Compose runtime
    implementation(libs.androidx.lifecycle.viewmodel.compose.v251) // ViewModel Compose integration
    implementation(libs.androidx.lifecycle.runtime.ktx.v251)

    // Hilt core library
    implementation(libs.hilt.android)

//    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.hilt.navigation.compose)

//    implementation (libs.androidx.hilt.lifecycle.viewmodel)
//    implementation (libs.hilt.android.v242)

//    kapt("groupId:artifactId:version")

    // Hilt compiler
//    ksp(libs.hilt.android.compiler)

//    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler)
//    kapt(libs.hilt.android.v2511)

    implementation (libs.accompanist.placeholder.material)

//    implementation("com.facebook.shimmer:shimmer:0.5.0")

    implementation (libs.androidx.animation)

    implementation (libs.androidx.compose.material3.material3)

//    implementation (libs.rxjava)
//    implementation (libs.rxkotlin)

    implementation (libs.rxjava3.rxandroid)
    implementation (libs.rxjava3.rxjava)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}