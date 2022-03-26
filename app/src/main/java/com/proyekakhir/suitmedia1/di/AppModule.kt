package com.proyekakhir.suitmedia1.di

import com.proyekakhir.suitmedia1.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { HomeViewModel(get()) }
}