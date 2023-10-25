package com.example.swimminginstructorlocator.utils

import android.app.Application

class SwimmingInstructorLocatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataLocalManager.init(applicationContext)
    }
}
