package com.example.clicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel : ViewModel() {
    private val _countMoney = MutableStateFlow(0)
    val countMoney: StateFlow<Int> = _countMoney

    private var sizeClick = 1
    private val updateCostImprovement = UpdateCostImprovement()
    fun handleClickAction(increment:Int){
        _countMoney.value += increment
    }
    fun clickOnTap():Int{
        return sizeClick
    }
    fun handleButtonMulti(){
        _countMoney.value -= updateCostImprovement.getUpdateCostMulti()
        updateCostImprovement.buyUpdateCountMulti()
        sizeClick *= 2
    }
}