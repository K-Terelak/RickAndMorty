package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.details.DetailsViewModel
import presentation.list.ListViewModel

val viewModelModule = module {
    singleOf(::ListViewModel)
    singleOf(::DetailsViewModel)
}
