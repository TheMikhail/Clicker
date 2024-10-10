package com.example.clicker

interface UpgradeManager {
    fun upgrade()
    fun getLevel(): Int
    fun getCostUpgrade(): Int
}