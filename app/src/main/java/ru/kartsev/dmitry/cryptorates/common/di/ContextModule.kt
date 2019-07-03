package ru.kartsev.dmitry.cryptorates.common.di

import android.content.Context
import android.content.res.Resources
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object ContextModule {
    /** Section: Constants. */

    const val RESOURCES_NAME = "context.resources"

    /** Section: Modules. */

    val it: Module = module {
        single<Resources>(RESOURCES_NAME) {
            get<Context>().resources
        }
    }
}