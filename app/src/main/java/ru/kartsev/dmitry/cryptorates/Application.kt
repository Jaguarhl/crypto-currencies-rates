package ru.kartsev.dmitry.cryptorates

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import org.koin.log.EmptyLogger
import ru.kartsev.dmitry.cryptorates.common.di.ContextModule
import ru.kartsev.dmitry.cryptorates.common.di.NetworkModule
import timber.log.Timber

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialize Koin DI modules.
        startKoin(
            this,
            listOf(ContextModule.it, NetworkModule.it),
            logger = if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
        )
    }
}