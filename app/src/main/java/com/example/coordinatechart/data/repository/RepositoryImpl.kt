package com.example.coordinatechart.data.repository

import com.example.coordinatechart.data.data_source.NetworkDataSourceApi
import com.example.coordinatechart.data.mapper.toDomain
import com.example.coordinatechart.domen.entity.Coordinate
import com.example.coordinatechart.domen.repository.RepositoryApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSourceApi) :
    RepositoryApi {
    override suspend fun getPoints(countValue: Int): Coordinate {
      return networkDataSource.getCoordinate(countValue).toDomain()
    }
}