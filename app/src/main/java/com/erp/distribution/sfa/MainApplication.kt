package com.erp.distribution.sfa

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var authHeader: String = "abcd"
    }

}