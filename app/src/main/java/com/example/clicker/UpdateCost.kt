package com.example.clicker

import kotlin.math.pow

class UpdateCost {
    private var baseCost = 10
    private var updateCount = 1
    private val costIncreaseFactor = 1.15

    fun getUpdateCost(): Double {
        return (baseCost * costIncreaseFactor.pow(updateCount))
    }

    fun buyUpdateCount(){
        updateCount++
    }
}