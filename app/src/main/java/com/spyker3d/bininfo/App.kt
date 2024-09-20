package com.spyker3d.bininfo

import android.app.Application
import com.spyker3d.bininfo.di.dataModule
import com.spyker3d.bininfo.di.interactorModule
import com.spyker3d.bininfo.di.repositoryModule
import com.spyker3d.bininfo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, interactorModule, repositoryModule, viewModelModule)
        }
    }
}