package di

import data.remote.provideClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::provideClient)
}
