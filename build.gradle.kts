// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    kotlin("kapt") version "2.1.0"
}

buildscript {
    repositories {
        google()  // This is required for Hilt and KSP plugins
        mavenCentral()  // General Maven repositories
        gradlePluginPortal()  // This is for Gradle plugins
    }
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.gradle) // Make sure to use the correct Android Gradle Plugin version
        classpath(libs.hilt.android.gradle.plugin) // Hilt Gradle plugin
    }
}