package com.example.clicker

import android.util.Log
import kotlin.math.pow

class UpdateCostImprovement {
    private val baseCost = 1
    private var updateCount = 1
    private val costIncreaseFactor = 2

    fun getUpdateCost(): Int {
        return ((baseCost * Math.pow(costIncreaseFactor.toDouble(),updateCount.toDouble())).toInt())
    }
    fun buyUpdateCount(){
        updateCount++
    }
}