package com.example.coordinatechart.data.mapper

import com.example.coordinatechart.data.model.PointItemResponse
import com.example.coordinatechart.data.model.CoordinateResponse
import com.example.coordinatechart.domen.entity.PointItem
import com.example.coordinatechart.domen.entity.Coordinate
import retrofit2.Response

fun CoordinateResponse.toDomain() = Coordinate(
    pointList = pointList.map {
        it.toDomain()
    }
)

fun PointItemResponse.toDomain() = PointItem(
    coordinateX = coordinateX,
    coordinateY = coordinateY
)