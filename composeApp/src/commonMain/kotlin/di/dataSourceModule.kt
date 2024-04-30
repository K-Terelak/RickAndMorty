package di

import data.remote.NetworkCharactersDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataSourceModule = module {
    singleOf(::NetworkCharactersDataSource)
}
