package com.example.coordinatechart.data.model

import com.google.gson.annotations.SerializedName

data class PointItemResponse(
    @SerializedName("x" ) val coordinateX : Double,
    @SerializedName("y" ) val coordinateY : Double
)