package com.test.batman.di

import android.app.Application
import androidx.room.Room
import com.test.batman.data.local.BatmanDatabase
import com.test.batman.data.local.LocalRepository
import com.test.batman.data.network.ApiClient
import com.test.batman.data.network.Configuration
import com.test.batman.view.dashboard.DashboardViewModel
import org.koin.android.ext.koin.androidApplication
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

val databaseModule = module {
    fun provideDatabase(application: Application): BatmanDatabase {
        return Room.databaseBuilder(application, BatmanDatabase::class.java, "Batman.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { provideDatabase(androidApplication()) }
    single { LocalRepository(get()) }
}

val listModule = arrayListOf(appModule, networkModules, viewModels, databaseModule)

