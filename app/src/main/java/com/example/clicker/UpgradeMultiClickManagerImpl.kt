package com.example.clicker

import kotlin.math.pow

object UpgradeMultiClickManagerImpl : UpgradeManager {
    private const val BASE_COST = 10
    private const val COST_INCREASE_FACTOR = 2f

    private var currentLevel = 1

    override fun upgrade() {
        currentLevel++
    }

    override fun getLevel(): Int = currentLevel

    override fun getCostUpgrade(): Int = BASE_COST * COST_INCREASE_FACTOR.pow(currentLevel).toInt()

}