package com.example.coordinatechart.domen.usecase

import com.example.coordinatechart.domen.entity.Coordinate

interface GetCoordinateUseCaseApi {
    suspend fun execute(countValue: Int): Coordinate
}