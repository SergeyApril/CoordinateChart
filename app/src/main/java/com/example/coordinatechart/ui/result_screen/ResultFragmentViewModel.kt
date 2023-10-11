package com.example.coordinatechart.ui.result_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.coordinatechart.domen.entity.AppData
import com.example.coordinatechart.domen.entity.PointItem
import javax.inject.Inject

class ResultFragmentViewModel @Inject constructor(
    private val getAppData: AppData
) : ViewModel() {

    private val points = getAppData.getResultList()

    private fun sortPoint(pointList: MutableList<PointItem>): MutableList<PointItem> {
        pointList.sortBy { it.coordinateX }
        Log.d("CoordinateApp ", "point is $pointList.toString()")
        return pointList
    }

    fun getList(): MutableList<PointItem> {
        return sortPoint(points)
    }
}