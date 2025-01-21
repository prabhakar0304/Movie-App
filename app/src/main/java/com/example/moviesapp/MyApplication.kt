package com.example.moviesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// This is the Application class where Hilt's dependency injection is initialized.
// The @HiltAndroidApp annotation triggers Hilt's code generation for this app.
@HiltAndroidApp
class MyApplication : Application()