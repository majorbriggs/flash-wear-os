package com.majorbriggs.flash.wear

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlashApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }
}
