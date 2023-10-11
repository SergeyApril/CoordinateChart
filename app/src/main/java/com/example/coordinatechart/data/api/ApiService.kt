package com.example.coordinatechart.data.api

import com.example.coordinatechart.data.model.CoordinateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("points")
    suspend fun getCoordinate(@Query("count") count: Int): CoordinateResponse
}