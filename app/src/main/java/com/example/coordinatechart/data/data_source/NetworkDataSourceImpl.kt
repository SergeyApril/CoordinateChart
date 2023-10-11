package com.example.coordinatechart.data.data_source

import com.example.coordinatechart.data.api.ApiService
import com.example.coordinatechart.data.model.CoordinateResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    NetworkDataSourceApi {
    override suspend fun getCoordinate(countValue: Int): CoordinateResponse {
        return apiService.getCoordinate(countValue)
    }
}