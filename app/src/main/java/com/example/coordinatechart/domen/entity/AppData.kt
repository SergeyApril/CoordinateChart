package com.example.coordinatechart.domen.entity

import javax.inject.Inject

class AppData @Inject constructor() {
    private var inputAmount: Int? = null
    private var resultList = mutableListOf<PointItem>()

    fun setInputAmount(value: Int) {
        inputAmount = value
    }

    fun getInputAmount() = inputAmount

    fun setResultList(list: List<PointItem>) {
        resultList.clear()
        resultList.addAll(list)
    }

    fun getResultList() = resultList
}
