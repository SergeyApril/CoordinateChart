package com.example.coordinatechart.domen.repository

import com.example.coordinatechart.domen.entity.Coordinate

interface RepositoryApi {
    suspend fun getPoints(countValue: Int): Coordinate
}