package com.picpay.desafio.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyAppication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}