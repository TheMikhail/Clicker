package com.example.clicker

class UpdateCostImprovement {
    private val baseCost = 10
    private var updateCount = 1
    private val costIncreaseFactor = 2

    private val baseCostAuto = 1
    private val costIncreaseFactorAuto = 3
    private var updateCountAuto = 1
    fun getUpdateCostMulti(): Int {
        return ((baseCost * Math.pow(costIncreaseFactor.toDouble(),updateCount.toDouble())).toInt())
    }
    fun buyUpdateCountMulti(){
        updateCount++
    }
    fun getUpdateCostAuto():Int{
        return ((baseCostAuto * Math.pow(costIncreaseFactorAuto.toDouble(),updateCountAuto.toDouble())).toInt())
    }
    fun buyUpdateCountAuto(){
        updateCountAuto++
    }
}