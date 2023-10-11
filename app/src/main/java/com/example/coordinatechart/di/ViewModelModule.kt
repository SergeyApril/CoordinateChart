package com.example.coordinatechart.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coordinatechart.ui.MainActivityViewModel
import com.example.coordinatechart.ui.result_screen.ResultFragmentViewModel
import com.example.coordinatechart.ui.start_screen.StartFragmentViewModel
import com.example.coordinatechart.util.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    @Binds
    abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ResultFragmentViewModel::class)
    @Binds
    abstract fun provideResultFragmentViewModel(resultFragmentViewMode: ResultFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(StartFragmentViewModel::class)
    @Binds
    abstract fun provideStartFragmentViewModel(startFragmentViewModel: StartFragmentViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @MapKey
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}