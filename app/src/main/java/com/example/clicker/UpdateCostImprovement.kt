package com.example.clicker

class UpdateCostImprovement {
    private val baseCost = 10
    var upgradeCountMulti = 1

    private val costIncreaseFactor = 2

    private val baseCostAuto = 3
    private val costIncreaseFactorAuto = 3
    var upgradeCountAuto = 1
    fun getUpdateCostMulti(): Int {
        return ((baseCost * Math.pow(costIncreaseFactor.toDouble(),upgradeCountMulti.toDouble())).toInt())
    }
    fun buyUpdateCountMulti(){
        upgradeCountMulti++
    }
    fun getUpdateCostAuto():Int{
        return ((baseCostAuto * Math.pow(costIncreaseFactorAuto.toDouble(),upgradeCountAuto.toDouble())).toInt())
    }
    fun buyUpdateCountAuto(){
        upgradeCountAuto++
    }

}