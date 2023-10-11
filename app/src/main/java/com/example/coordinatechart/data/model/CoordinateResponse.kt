package com.example.coordinatechart.data.model

import com.google.gson.annotations.SerializedName

data class CoordinateResponse(
    @SerializedName("points" ) val pointList : List<PointItemResponse>
)