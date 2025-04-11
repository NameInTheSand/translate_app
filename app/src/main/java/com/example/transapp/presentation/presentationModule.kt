package com.example.transapp.presentation

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<TranslateScreenViewModel> { TranslateScreenViewModel(get()) }
}