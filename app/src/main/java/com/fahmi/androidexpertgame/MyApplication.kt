package com.fahmi.androidexpertgame

import android.app.Application
import com.fahmi.androidexpertgame.core.di.databaseModule
import com.fahmi.androidexpertgame.core.di.networkModule
import com.fahmi.androidexpertgame.core.di.repositoryModule
import com.fahmi.androidexpertgame.core.di.useCaseModule
import com.fahmi.androidexpertgame.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}