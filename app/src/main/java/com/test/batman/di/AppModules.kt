package com.test.batman.di

import com.test.batman.data.network.ApiClient
import com.test.batman.data.network.Configuration
import com.test.batman.view.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Configuration() }
}

val networkModules = module {
    factory { ApiClient(get()).getInterface() }
}

val viewModels = module {
    viewModel { DashboardViewModel(get())}
}

val listModule = arrayListOf(appModule, networkModules, viewModels)

