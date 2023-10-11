package com.example.coordinatechart.di

import com.example.coordinatechart.data.data_source.NetworkDataSourceApi
import com.example.coordinatechart.data.data_source.NetworkDataSourceImpl
import com.example.coordinatechart.data.repository.RepositoryImpl
import com.example.coordinatechart.domen.entity.AppData
import com.example.coordinatechart.domen.repository.RepositoryApi
import com.example.coordinatechart.domen.usecase.GetCoordinateUseCaseApi
import com.example.coordinatechart.domen.usecase.GetCoordinateUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    fun bindNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSourceApi

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): RepositoryApi

    @Binds
    fun bindCoordinateUseCase(getCoordinateUseCase: GetCoordinateUseCaseImpl): GetCoordinateUseCaseApi

    companion object{
        @Singleton
        @Provides
        fun provideAppData() : AppData = AppData()
    }
}