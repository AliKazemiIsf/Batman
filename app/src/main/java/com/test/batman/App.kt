package com.test.batman

import android.app.Application
import com.test.batman.di.listModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listModule)
        }
    }
}