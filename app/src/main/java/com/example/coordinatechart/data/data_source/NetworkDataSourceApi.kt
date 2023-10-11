package com.example.coordinatechart.data.data_source

import com.example.coordinatechart.data.model.CoordinateResponse
import retrofit2.Response

interface NetworkDataSourceApi {
    suspend fun getCoordinate(countValue: Int): CoordinateResponse
}