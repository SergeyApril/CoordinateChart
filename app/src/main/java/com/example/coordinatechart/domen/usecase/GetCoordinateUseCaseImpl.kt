package com.example.coordinatechart.domen.usecase

import com.example.coordinatechart.domen.entity.Coordinate
import com.example.coordinatechart.domen.repository.RepositoryApi
import javax.inject.Inject

class GetCoordinateUseCaseImpl @Inject constructor(private val repository: RepositoryApi) :
    GetCoordinateUseCaseApi {
    override suspend fun execute(countValue: Int): Coordinate {
        return repository.getPoints(countValue)
    }

}