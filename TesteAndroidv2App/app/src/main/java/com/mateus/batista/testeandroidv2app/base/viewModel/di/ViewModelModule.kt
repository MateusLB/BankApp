package com.mateus.batista.testeandroidv2app.base.viewModel.di

import android.arch.lifecycle.ViewModelProvider
import com.mateus.batista.testeandroidv2app.base.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}