package di

import data.remote.repository.RickMortyRepositoryImpl
import domain.repository.RickMortyRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::RickMortyRepositoryImpl) {
        bind<RickMortyRepository>()
    }
}
