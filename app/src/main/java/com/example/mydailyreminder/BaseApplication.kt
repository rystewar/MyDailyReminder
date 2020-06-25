package com.example.mydailyreminder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Need to create custom application class in order to use hilt di
@HiltAndroidApp
class BaseApplication : Application() {
}