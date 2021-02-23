package com.huyqgtran.queenlive.application

import android.app.Application
import com.huyqgtran.queenlive.BuildConfig
import com.huyqgtran.queenlive.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class QueenLiveApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@QueenLiveApplication)
            modules(listOf(applicationModule))
        }
    }
}